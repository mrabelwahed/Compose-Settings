package com.ramadanapps.composesettings

import androidx.lifecycle.ViewModel
import com.ramadanapps.composesettings.model.MarketingOptions
import com.ramadanapps.composesettings.model.SettingsState
import com.ramadanapps.composesettings.model.Theme
import kotlinx.coroutines.flow.MutableStateFlow

class SettingsViewModel: ViewModel() {
     val uiState = MutableStateFlow(SettingsState())

    fun toggleNotificationsSettings(status: Boolean) {
       uiState.value =
           uiState.value.copy(
               notificationsEnabled = status
           )
    }

    fun toggleHintSSettings(isSelected: Boolean) {
        uiState.value =
            uiState.value.copy(
                hintEnabled =  isSelected
            )
    }

    fun setMarketingSettings(option: MarketingOptions){
        uiState.value =
            uiState.value.copy(
                marketingOptions = option
            )
    }

    fun setTheme(theme: Theme) {
      uiState.value =
          uiState.value.copy(
              selectedTheme = theme
          )
    }
}