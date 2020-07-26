package com.marcusrunge.roominfo.dagger.settings

import com.marcusrunge.roominfo.dagger.applicationresource.ApplicationResourceModule
import com.marcusrunge.roominfo.dagger.data.DataModule
import com.marcusrunge.roominfo.dagger.settingsservice.SettingsServiceModule
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.interfaces.SettingsService
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.ui.settings.SettingsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationResourceModule::class, SettingsServiceModule::class, DataModule::class])
interface SettingsViewModelComponent {
    fun inject(settingsViewModel: SettingsViewModel)
    fun provideApplicationResource(): ApplicationResource
    fun provideSettingsService(): SettingsService
    fun provideData(): Data
}