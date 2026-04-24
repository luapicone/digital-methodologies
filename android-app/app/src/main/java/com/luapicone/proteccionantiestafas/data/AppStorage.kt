package com.luapicone.proteccionantiestafas.data

import android.content.Context
import android.content.SharedPreferences
import com.luapicone.proteccionantiestafas.ui.AppUiState
import com.luapicone.proteccionantiestafas.ui.EventType
import com.luapicone.proteccionantiestafas.ui.HistoryEventUi
import com.luapicone.proteccionantiestafas.ui.InterventionLevel
import com.luapicone.proteccionantiestafas.ui.OnboardingUiState
import com.luapicone.proteccionantiestafas.ui.TrustedContactUi
import com.luapicone.proteccionantiestafas.ui.UserSettingsUi
import org.json.JSONArray
import org.json.JSONObject

object AppStorage {
    private const val PREFS = "proteccion_antiestafas_prefs"
    private const val KEY_STATE = "app_state_json"

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        if (!::prefs.isInitialized) {
            prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        }
    }

    fun loadState(): AppUiState? {
        if (!::prefs.isInitialized) return null
        val raw = prefs.getString(KEY_STATE, null) ?: return null
        return runCatching {
            val root = JSONObject(raw)
            val contacts = root.optJSONArray("contacts")?.toTrustedContacts().orEmpty()
            val events = root.optJSONArray("events")?.toHistoryEvents().orEmpty()
            AppUiState(
                onboarding = OnboardingUiState(
                    step = root.optInt("onboardingStep", 0),
                    name = root.optString("onboardingName", ""),
                    interventionLevel = root.optString("onboardingLevel", InterventionLevel.ASSISTED.name).toInterventionLevel(),
                ),
                isOnboardingCompleted = root.optBoolean("isOnboardingCompleted", false),
                userName = root.optString("userName", ""),
                contacts = if (contacts.isEmpty()) AppUiState().contacts else contacts,
                selectedContactId = if (root.has("selectedContactId") && !root.isNull("selectedContactId")) root.optString("selectedContactId") else null,
                interventionLevel = root.optString("interventionLevel", InterventionLevel.ASSISTED.name).toInterventionLevel(),
                settings = UserSettingsUi(
                    notificationsEnabled = root.optBoolean("notificationsEnabled", true),
                    suggestContactOnHighRisk = root.optBoolean("suggestContactOnHighRisk", true),
                    showCallWarnings = root.optBoolean("showCallWarnings", true),
                    allowSharedTextAnalysis = root.optBoolean("allowSharedTextAnalysis", true),
                ),
                recentEvents = if (events.isEmpty()) AppUiState().recentEvents else events,
                activeFilter = null,
            )
        }.getOrNull()
    }

    fun saveState(state: AppUiState) {
        if (!::prefs.isInitialized) return
        val root = JSONObject().apply {
            put("onboardingStep", state.onboarding.step)
            put("onboardingName", state.onboarding.name)
            put("onboardingLevel", state.onboarding.interventionLevel.name)
            put("isOnboardingCompleted", state.isOnboardingCompleted)
            put("userName", state.userName)
            put("selectedContactId", state.selectedContactId)
            put("interventionLevel", state.interventionLevel.name)
            put("notificationsEnabled", state.settings.notificationsEnabled)
            put("suggestContactOnHighRisk", state.settings.suggestContactOnHighRisk)
            put("showCallWarnings", state.settings.showCallWarnings)
            put("allowSharedTextAnalysis", state.settings.allowSharedTextAnalysis)
            put("contacts", JSONArray().apply {
                state.contacts.forEach { contact ->
                    put(JSONObject().apply {
                        put("id", contact.id)
                        put("name", contact.name)
                        put("phone", contact.phone)
                        put("relationship", contact.relationship)
                    })
                }
            })
            put("events", JSONArray().apply {
                state.recentEvents.forEach { event ->
                    put(JSONObject().apply {
                        put("id", event.id)
                        put("type", event.type.name)
                        put("title", event.title)
                        put("detail", event.detail)
                        put("timestamp", event.timestamp)
                    })
                }
            })
        }
        prefs.edit().putString(KEY_STATE, root.toString()).apply()
    }

    private fun JSONArray.toTrustedContacts(): List<TrustedContactUi> {
        return buildList {
            for (i in 0 until length()) {
                val item = optJSONObject(i) ?: continue
                add(
                    TrustedContactUi(
                        id = item.optString("id"),
                        name = item.optString("name"),
                        phone = item.optString("phone"),
                        relationship = item.optString("relationship"),
                    )
                )
            }
        }
    }

    private fun JSONArray.toHistoryEvents(): List<HistoryEventUi> {
        return buildList {
            for (i in 0 until length()) {
                val item = optJSONObject(i) ?: continue
                add(
                    HistoryEventUi(
                        id = item.optString("id"),
                        type = runCatching { EventType.valueOf(item.optString("type")) }.getOrDefault(EventType.MESSAGE_ANALYSIS),
                        title = item.optString("title"),
                        detail = item.optString("detail"),
                        timestamp = item.optString("timestamp"),
                    )
                )
            }
        }
    }

    private fun String.toInterventionLevel(): InterventionLevel {
        return runCatching { InterventionLevel.valueOf(this) }.getOrDefault(InterventionLevel.ASSISTED)
    }
}
