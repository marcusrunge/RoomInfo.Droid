package com.marcusrunge.roominfo.dagger.preferences

import com.marcusrunge.roominfo.dagger.applicationresource.ApplicationResourceModule
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.preferences.implementations.PreferencesFactoryImpl
import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationResourceModule::class])
class PreferencesModule {
    @Provides
    @Singleton
    fun providePreferenceService(applicationResource: ApplicationResource): Preferences {
        return PreferencesFactoryImpl.createSingleton(applicationResource.applicationContext!!)
    }
}