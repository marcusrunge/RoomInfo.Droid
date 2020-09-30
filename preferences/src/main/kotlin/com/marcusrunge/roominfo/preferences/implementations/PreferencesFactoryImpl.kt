package com.marcusrunge.roominfo.preferences.implementations

import android.content.Context
import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import com.marcusrunge.roominfo.preferences.interfaces.PreferencesFactory

class PreferencesFactoryImpl {
    companion object : PreferencesFactory {
        var preferences: Preferences? = null
        override fun createSingleton(context: Context): Preferences = when {
            preferences != null -> preferences!!
            else -> {
                preferences = PreferencesImpl(context)
                preferences!!
            }
        }
    }
}