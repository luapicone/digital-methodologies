package com.luapicone.proteccionantiestafas.ui

import androidx.compose.runtime.Immutable

@Immutable
data class TrustedContactUi(
    val name: String,
    val phone: String,
    val relationship: String,
)

@Immutable
data class AnalysisResultUi(
    val riskLabel: String,
    val score: Int,
    val signals: List<String>,
    val recommendation: String,
)

@Immutable
data class AppUiState(
    val contact: TrustedContactUi = TrustedContactUi("Lucía", "+54 11 5555 5555", "Hija"),
    val interventionLevel: String = "Modo acompañado",
    val recentEvents: List<String> = listOf(
        "Mensaje analizado: Riesgo alto",
        "Alerta enviada a contacto de confianza",
        "Llamada no agendada detectada"
    )
)

fun analyzeMessage(input: String): AnalysisResultUi {
    val normalized = input.lowercase()
    val signals = buildList {
        if ("urgente" in normalized || "ahora" in normalized || "ya" in normalized) add("Pedido urgente")
        if ("transfer" in normalized || "dinero" in normalized || "pago" in normalized) add("Pedido de dinero o transferencia")
        if ("banco" in normalized || "cuenta" in normalized || "clave" in normalized || "código" in normalized) add("Suplantación bancaria o pedido de código")
        if ("mamá" in normalized || "papá" in normalized || "hijo" in normalized || "familiar" in normalized) add("Apelación a vínculo familiar")
        if ("http" in normalized || "www" in normalized || "link" in normalized) add("Link sospechoso")
    }

    val score = when {
        signals.size >= 4 -> 90
        signals.size == 3 -> 75
        signals.size == 2 -> 55
        signals.size == 1 -> 35
        else -> 15
    }

    val risk = when {
        score >= 80 -> "Riesgo alto"
        score >= 50 -> "Riesgo medio"
        else -> "Riesgo bajo"
    }

    val recommendation = when {
        score >= 80 -> "No respondas ni transfieras dinero. Verificá por otro medio y avisá a tu contacto de confianza."
        score >= 50 -> "Antes de actuar, verificá si el pedido es real y no compartas datos sensibles."
        else -> "No se detectaron señales fuertes, pero igual conviene verificar si hay dudas."
    }

    return AnalysisResultUi(
        riskLabel = risk,
        score = score,
        signals = if (signals.isEmpty()) listOf("Sin señales fuertes detectadas") else signals,
        recommendation = recommendation,
    )
}
