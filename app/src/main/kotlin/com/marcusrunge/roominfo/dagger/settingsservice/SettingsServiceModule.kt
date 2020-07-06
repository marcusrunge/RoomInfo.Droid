package com.marcusrunge.roominfo.dagger.settingsservice

import android.content.Context
import com.marcusrunge.roominfo.dagger.ApplicationContextModule
import com.marcusrunge.roominfo.implementations.SettingsServiceImpl
import com.marcusrunge.roominfo.interfaces.SettingsService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationContextModule::class])
class SettingsServiceModule {
    @Provides
    @Singleton
    fun provideSettingsService(context: Context): SettingsService {
        return SettingsServiceImpl.getInstance(context)
    }
}