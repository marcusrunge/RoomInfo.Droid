package com.marcusrunge.roominfo.dagger.settingsservice

import com.marcusrunge.roominfo.dagger.applicationresource.ApplicationResourceModule
import com.marcusrunge.roominfo.implementations.SettingsServiceImpl
import com.marcusrunge.roominfo.interfaces.SettingsService
import com.marcusrunge.roominfo.models.ApplicationResource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationResourceModule::class])
class SettingsServiceModule {
    @Provides
    @Singleton
    fun provideSettingsService(applicationResource: ApplicationResource): SettingsService {
        return SettingsServiceImpl.getInstance(applicationResource.applicationContext!!)
    }
}