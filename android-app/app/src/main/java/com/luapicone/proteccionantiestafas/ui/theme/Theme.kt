package com.luapicone.proteccionantiestafas.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val AppColors = darkColorScheme(
    primary = Orange500,
    secondary = Orange300,
    tertiary = Success,
    background = Navy900,
    surface = Navy800,
    surfaceVariant = Navy700,
    onPrimary = Navy900,
    onSecondary = Navy900,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onSurfaceVariant = TextMuted,
)

@Composable
fun ProteccionAntiestafasTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColors,
        typography = Typography,
        content = content,
    )
}
