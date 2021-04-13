package com.marcusrunge.roominfo.dagger.time

import com.marcusrunge.roominfo.dagger.applicationresource.ApplicationResourceModule
import com.marcusrunge.roominfo.dagger.data.DataModule
import com.marcusrunge.roominfo.dagger.preferences.PreferencesModule
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import com.marcusrunge.roominfo.time.implementations.TimeFactoryImpl
import com.marcusrunge.roominfo.time.interfaces.Time
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationResourceModule::class, DataModule::class, PreferencesModule::class])
class TimeModule {
    @Provides
    @Singleton
    fun provideTime(
        applicationResource: ApplicationResource,
        data: Data,
        preferences: Preferences
    ): Time {
        return TimeFactoryImpl.createSingleton(
            applicationResource.applicationContext!!,
            data,
            preferences
        )
    }
}