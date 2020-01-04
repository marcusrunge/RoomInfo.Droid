package com.marcusrunge.roominfo.dagger.settingsservice

import com.marcusrunge.roominfo.interfaces.ISettingsService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SettingsServiceModule::class])
interface SettingsServiceComponent {
    fun provideSettingsService(): ISettingsService
}