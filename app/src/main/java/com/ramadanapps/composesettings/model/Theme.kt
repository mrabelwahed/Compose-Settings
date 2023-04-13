package com.ramadanapps.composesettings.model

import androidx.annotation.StringRes
import com.ramadanapps.composesettings.R

enum class Theme(@StringRes val label: Int) {
    LIGHT(R.string.theme_light),
    DARK(R.string.theme_dark),
    SYSTEM(R.string.theme_system)
}