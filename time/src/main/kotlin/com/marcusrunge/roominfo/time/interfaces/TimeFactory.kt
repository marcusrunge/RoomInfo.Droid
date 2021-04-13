package com.marcusrunge.roominfo.time.interfaces

import android.content.Context
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.preferences.interfaces.Preferences

interface TimeFactory {
    fun createSingleton(context: Context, data: Data, preferences: Preferences): Time
}