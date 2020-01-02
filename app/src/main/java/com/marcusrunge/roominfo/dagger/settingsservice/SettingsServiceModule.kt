package com.marcusrunge.roominfo.dagger.settingsservice

import android.content.Context
import com.marcusrunge.roominfo.dagger.ApplicationContextModule
import com.marcusrunge.roominfo.interfaces.Settings
import com.marcusrunge.roominfo.services.SettingsService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationContextModule::class])
class SettingsServiceModule {
    @Provides
    @Singleton
    fun provideSettingsService(context: Context): Settings {
        return SettingsService.getInstance(context)
    }
}