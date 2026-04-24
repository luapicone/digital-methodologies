package com.luapicone.proteccionantiestafas.ui

sealed class Screen(val route: String) {
    data object Onboarding : Screen("onboarding")
    data object Home : Screen("home")
    data object Analyze : Screen("analyze")
    data object Help : Screen("help")
    data object Contacts : Screen("contacts")
    data object Settings : Screen("settings")
    data object History : Screen("history")
}
