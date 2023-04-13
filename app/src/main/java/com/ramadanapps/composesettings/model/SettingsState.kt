package com.ramadanapps.composesettings.model

data class SettingsState(
    val notificationsEnabled: Boolean = false,
    val hintEnabled: Boolean = false,
    val marketingOptions: MarketingOptions = MarketingOptions.NOT_ALLOWED,
    val selectedTheme: Theme = Theme.SYSTEM
)
