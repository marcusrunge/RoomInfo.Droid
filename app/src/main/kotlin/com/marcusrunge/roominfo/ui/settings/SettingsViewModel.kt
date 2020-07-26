package com.marcusrunge.roominfo.ui.settings

import android.content.Context
import com.marcusrunge.roominfo.interfaces.SettingsService
import com.marcusrunge.roominfo.ui.ViewModelBase
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val context: Context,
    private val settingsService: SettingsService
) : ViewModelBase()