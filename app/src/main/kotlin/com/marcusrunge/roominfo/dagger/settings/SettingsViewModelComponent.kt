package com.marcusrunge.roominfo.dagger.settings

import android.content.Context
import com.marcusrunge.roominfo.dagger.ApplicationContextModule
import com.marcusrunge.roominfo.dagger.data.DataModule
import com.marcusrunge.roominfo.dagger.settingsservice.SettingsServiceModule
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.interfaces.SettingsService
import com.marcusrunge.roominfo.ui.settings.SettingsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationContextModule::class, SettingsServiceModule::class, DataModule::class])
interface SettingsViewModelComponent {
    fun inject(settingsViewModel: SettingsViewModel)
    fun provideContext(): Context
    fun provideSettingsService(): SettingsService
    fun provideData(): Data
}