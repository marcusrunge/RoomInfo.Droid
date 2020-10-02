package com.marcusrunge.roominfo.time.interfaces

import android.content.Context

interface TimeFactory {
    fun createSingleton(context: Context): Time
}