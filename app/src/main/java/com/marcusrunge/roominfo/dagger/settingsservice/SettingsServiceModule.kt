package com.marcusrunge.roominfo.dagger.settingsservice

import android.content.Context
import com.marcusrunge.roominfo.dagger.ApplicationContextModule
import com.marcusrunge.roominfo.interfaces.ISettingsService
import com.marcusrunge.roominfo.services.SettingsServiceService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationContextModule::class])
class SettingsServiceModule {
    @Provides
    @Singleton
    fun provideSettingsService(context: Context): ISettingsService {
        return SettingsServiceService.getInstance(context)
    }
}