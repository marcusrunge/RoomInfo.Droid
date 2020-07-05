package com.marcusrunge.roominfo.dagger.settings

import android.content.Context
import com.marcusrunge.roominfo.dagger.ApplicationContextModule
import com.marcusrunge.roominfo.ui.settings.SettingsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationContextModule::class])
interface SettingsViewModelComponent {
    fun inject(settingsViewModel: SettingsViewModel)
    fun provideContext(): Context
}