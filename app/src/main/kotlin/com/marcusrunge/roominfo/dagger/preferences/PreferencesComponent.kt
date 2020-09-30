package com.marcusrunge.roominfo.dagger.preferences

import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PreferencesModule::class])
interface PreferencesComponent {
    fun providePreferences(): Preferences
}