package com.luapicone.proteccionantiestafas.platform

import com.luapicone.proteccionantiestafas.ui.NumberCheckResultUi

object CallRiskEngine {
    private val mockHighRiskNumbers = setOf(
        "+541150000001",
        "+541150000002",
        "08003452789",
        "1133334444",
    )

    fun evaluateIncomingNumber(rawNumber: String?, isKnownContact: Boolean): NumberCheckResultUi {
        val number = rawNumber.orEmpty().filter { it.isDigit() || it == '+' }

        if (isKnownContact) {
            return NumberCheckResultUi(
                riskLabel = "Contacto conocido",
                riskColor = "safe",
                detail = "El número figura como contacto conocido. Igual conviene verificar si piden dinero, códigos o actuar con urgencia.",
                suggestedActions = listOf("Responder con atención", "Desconfiar si piden plata o códigos"),
            )
        }

        if (number.isBlank()) {
            return NumberCheckResultUi(
                riskLabel = "Número oculto o vacío",
                riskColor = "warning",
                detail = "Si no podés identificar el número, no compartas datos ni tomes decisiones durante la llamada.",
                suggestedActions = listOf("No compartir información", "Cortar y verificar por otro medio"),
            )
        }

        if (mockHighRiskNumbers.contains(number)) {
            return NumberCheckResultUi(
                riskLabel = "Posible llamada riesgosa",
                riskColor = "danger",
                detail = "El número coincide con una base mock local de números reportados o altamente sospechosos dentro del MVP.",
                suggestedActions = listOf("No compartir claves", "No transferir dinero", "Verificar con contacto de confianza"),
            )
        }

        return if (number.startsWith("0800")) {
            NumberCheckResultUi(
                riskLabel = "Verificar identidad",
                riskColor = "warning",
                detail = "Que un número sea 0800 no prueba legitimidad. Si dicen ser del banco, cortá y llamá al canal oficial.",
                suggestedActions = listOf("Cortar y verificar", "No compartir códigos"),
            )
        } else {
            NumberCheckResultUi(
                riskLabel = "Número no identificado",
                riskColor = "warning",
                detail = "No hay coincidencia con la base mock local, pero sigue siendo un número no identificado. Actuá con cautela.",
                suggestedActions = listOf("Escuchar con cuidado", "No actuar bajo presión"),
            )
        }
    }
}
