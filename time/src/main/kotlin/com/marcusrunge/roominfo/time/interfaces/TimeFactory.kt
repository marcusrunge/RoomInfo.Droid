package com.marcusrunge.roominfo.time.interfaces

import android.content.Context
import com.marcusrunge.roominfo.data.interfaces.Data

interface TimeFactory {
    fun createSingleton(context: Context, data: Data): Time
}