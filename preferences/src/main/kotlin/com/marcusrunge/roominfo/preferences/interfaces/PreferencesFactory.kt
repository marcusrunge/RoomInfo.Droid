package com.marcusrunge.roominfo.preferences.interfaces

import android.content.Context

interface PreferencesFactory {
    fun createSingleton(context: Context): Preferences
}