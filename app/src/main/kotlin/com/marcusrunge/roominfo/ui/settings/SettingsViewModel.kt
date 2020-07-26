package com.marcusrunge.roominfo.ui.settings

import com.marcusrunge.roominfo.interfaces.SettingsService
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.ui.ViewModelBase
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val applicationResource: ApplicationResource,
    private val settingsService: SettingsService
) : ViewModelBase() {
    override fun updateView(obj: Any) {
        TODO("Not yet implemented")
    }
}