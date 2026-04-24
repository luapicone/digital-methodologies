package com.luapicone.proteccionantiestafas.ui

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.luapicone.proteccionantiestafas.data.AppStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

enum class InterventionLevel(val label: String, val description: String) {
    SOFT("Modo suave", "Solo alerta y explica el riesgo."),
    ASSISTED("Modo acompañado", "Alerta y sugiere pedir ayuda."),
    REINFORCED("Modo reforzado", "Prioriza el contacto de confianza y advertencias fuertes."),
}

enum class EventType(val label: String) {
    MESSAGE_ANALYSIS("Análisis de mensaje"),
    CALL_CHECK("Chequeo post-llamada"),
    HELP_REQUEST("Pedido de ayuda"),
}

@Immutable
data class TrustedContactUi(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val phone: String,
    val relationship: String,
)

@Immutable
data class AnalysisResultUi(
    val riskLabel: String,
    val riskColor: String,
    val score: Int,
    val severity: Int,
    val signals: List<String>,
    val recommendation: String,
    val suggestedActions: List<String>,
)

@Immutable
data class HistoryEventUi(
    val id: String = UUID.randomUUID().toString(),
    val type: EventType,
    val title: String,
    val detail: String,
    val timestamp: String,
)

@Immutable
data class UserSettingsUi(
    val notificationsEnabled: Boolean = true,
    val suggestContactOnHighRisk: Boolean = true,
    val showCallWarnings: Boolean = true,
    val allowSharedTextAnalysis: Boolean = true,
)

@Immutable
data class OnboardingUiState(
    val step: Int = 0,
    val name: String = "",
    val interventionLevel: InterventionLevel = InterventionLevel.ASSISTED,
)

@Immutable
data class AppUiState(
    val onboarding: OnboardingUiState = OnboardingUiState(),
    val isOnboardingCompleted: Boolean = false,
    val userName: String = "",
    val contacts: List<TrustedContactUi> = listOf(
        TrustedContactUi(name = "Lucía", phone = "+54 11 5555 5555", relationship = "Hija")
    ),
    val selectedContactId: String? = null,
    val interventionLevel: InterventionLevel = InterventionLevel.ASSISTED,
    val settings: UserSettingsUi = UserSettingsUi(),
    val recentEvents: List<HistoryEventUi> = listOf(
        HistoryEventUi(
            type = EventType.MESSAGE_ANALYSIS,
            title = "Mensaje analizado",
            detail = "Riesgo alto por urgencia y pedido de transferencia.",
            timestamp = nowLabel()
        ),
        HistoryEventUi(
            type = EventType.HELP_REQUEST,
            title = "Alerta a contacto",
            detail = "Se sugirió avisar a Lucía antes de responder.",
            timestamp = nowLabel()
        )
    ),
    val activeFilter: EventType? = null,
)

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppStorage.loadState() ?: AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    fun updateOnboardingName(value: String) {
        mutate { it.copy(onboarding = it.onboarding.copy(name = value)) }
    }

    fun updateOnboardingLevel(level: InterventionLevel) {
        mutate { it.copy(onboarding = it.onboarding.copy(interventionLevel = level)) }
    }

    fun nextOnboardingStep() {
        mutate { state ->
            val nextStep = (state.onboarding.step + 1).coerceAtMost(2)
            state.copy(onboarding = state.onboarding.copy(step = nextStep))
        }
    }

    fun previousOnboardingStep() {
        mutate { state ->
            val nextStep = (state.onboarding.step - 1).coerceAtLeast(0)
            state.copy(onboarding = state.onboarding.copy(step = nextStep))
        }
    }

    fun completeOnboarding() {
        mutate { state ->
            state.copy(
                isOnboardingCompleted = true,
                userName = state.onboarding.name.ifBlank { "Usuario" },
                interventionLevel = state.onboarding.interventionLevel,
            )
        }
    }

    fun addOrUpdateContact(name: String, phone: String, relationship: String) {
        if (name.isBlank() || phone.isBlank() || relationship.isBlank()) return
        mutate { state ->
            val newContact = TrustedContactUi(name = name, phone = phone, relationship = relationship)
            val updatedContacts = (state.contacts + newContact).takeLast(2)
            state.copy(contacts = updatedContacts, selectedContactId = newContact.id)
        }
    }

    fun selectContact(contactId: String) {
        mutate { it.copy(selectedContactId = contactId) }
    }

    fun updateInterventionLevel(level: InterventionLevel) {
        mutate { it.copy(interventionLevel = level) }
    }

    fun updateNotifications(enabled: Boolean) {
        mutate { it.copy(settings = it.settings.copy(notificationsEnabled = enabled)) }
    }

    fun updateSuggestContact(enabled: Boolean) {
        mutate { it.copy(settings = it.settings.copy(suggestContactOnHighRisk = enabled)) }
    }

    fun updateCallWarnings(enabled: Boolean) {
        mutate { it.copy(settings = it.settings.copy(showCallWarnings = enabled)) }
    }

    fun updateSharedAnalysis(enabled: Boolean) {
        mutate { it.copy(settings = it.settings.copy(allowSharedTextAnalysis = enabled)) }
    }

    fun registerMessageAnalysis(result: AnalysisResultUi) {
        appendHistory(
            HistoryEventUi(
                type = EventType.MESSAGE_ANALYSIS,
                title = "${result.riskLabel} (${result.score}/100)",
                detail = result.signals.joinToString(),
                timestamp = nowLabel(),
            )
        )
    }

    fun registerCallCheck(summary: String) {
        appendHistory(
            HistoryEventUi(
                type = EventType.CALL_CHECK,
                title = "Chequeo post-llamada",
                detail = summary,
                timestamp = nowLabel(),
            )
        )
    }

    fun registerHelpRequest() {
        val contactName = activeContact(_uiState.value)?.name ?: "tu contacto"
        appendHistory(
            HistoryEventUi(
                type = EventType.HELP_REQUEST,
                title = "Pedido de ayuda",
                detail = "Se inició un pedido de ayuda para $contactName.",
                timestamp = nowLabel(),
            )
        )
    }

    fun updateFilter(type: EventType?) {
        mutate { it.copy(activeFilter = type) }
    }

    fun activeContact(state: AppUiState = _uiState.value): TrustedContactUi? {
        return state.contacts.firstOrNull { it.id == state.selectedContactId }
            ?: state.contacts.firstOrNull()
    }

    private fun appendHistory(event: HistoryEventUi) {
        mutate { state ->
            state.copy(recentEvents = listOf(event) + state.recentEvents)
        }
    }

    private fun mutate(transform: (AppUiState) -> AppUiState) {
        _uiState.update { current ->
            transform(current).also { updated ->
                AppStorage.saveState(updated)
            }
        }
    }
}

fun analyzeMessage(input: String): AnalysisResultUi {
    val normalized = input.lowercase(Locale.ROOT)
    val signals = buildList {
        if ("urgente" in normalized || "ahora" in normalized || "ya" in normalized || "rápido" in normalized) add("Pedido urgente")
        if ("transfer" in normalized || "dinero" in normalized || "pago" in normalized || "deposit" in normalized) add("Pedido de dinero o transferencia")
        if ("banco" in normalized || "cuenta" in normalized || "clave" in normalized || "código" in normalized || "token" in normalized) add("Suplantación bancaria o pedido de credenciales")
        if ("mamá" in normalized || "papá" in normalized || "hijo" in normalized || "familiar" in normalized || "soy yo" in normalized) add("Apelación a vínculo familiar")
        if ("http" in normalized || "www" in normalized || "link" in normalized || ".com" in normalized) add("Link sospechoso")
        if ("no le digas a nadie" in normalized || "mantenelo en secreto" in normalized) add("Aislamiento o secreto")
        if ("premio" in normalized || "ganaste" in normalized || "beneficio" in normalized) add("Promesa sospechosa")
    }

    val score = when {
        signals.size >= 6 -> 96
        signals.size == 5 -> 90
        signals.size == 4 -> 82
        signals.size == 3 -> 72
        signals.size == 2 -> 54
        signals.size == 1 -> 32
        else -> 12
    }

    return when {
        score >= 80 -> AnalysisResultUi(
            riskLabel = "Riesgo alto",
            riskColor = "danger",
            score = score,
            severity = 3,
            signals = if (signals.isEmpty()) listOf("Sin señales fuertes detectadas") else signals,
            recommendation = "No respondas, no transfieras dinero y verificá por otro medio. Si podés, avisá ya a un contacto de confianza.",
            suggestedActions = listOf("No responder ahora", "Verificar por otro medio", "Avisar a contacto de confianza"),
        )

        score >= 50 -> AnalysisResultUi(
            riskLabel = "Riesgo medio",
            riskColor = "warning",
            score = score,
            severity = 2,
            signals = if (signals.isEmpty()) listOf("Sin señales fuertes detectadas") else signals,
            recommendation = "Antes de actuar, verificá si el pedido es real y evitá compartir datos sensibles o códigos.",
            suggestedActions = listOf("Verificar identidad", "No compartir códigos", "Esperar y consultar"),
        )

        else -> AnalysisResultUi(
            riskLabel = "Riesgo bajo",
            riskColor = "safe",
            score = score,
            severity = 1,
            signals = if (signals.isEmpty()) listOf("Sin señales fuertes detectadas") else signals,
            recommendation = "No se detectaron señales fuertes, pero si algo te incomoda conviene verificar antes de actuar.",
            suggestedActions = listOf("Seguir con atención", "Verificar si hay dudas"),
        )
    }
}

private fun nowLabel(): String {
    return SimpleDateFormat("dd/MM · HH:mm", Locale("es", "AR")).format(Date())
}
