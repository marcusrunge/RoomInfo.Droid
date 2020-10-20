package com.marcusrunge.roominfo.time.implementations

import android.content.Context
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.time.interfaces.Time
import com.marcusrunge.roominfo.time.interfaces.TimeFactory

class TimeFactoryImpl {
    companion object : TimeFactory {
        var time: Time? = null
        override fun createSingleton(context: Context, data: Data): Time = when {
            time != null -> time!!
            else -> {
                time = TimeImpl(context, data)
                time!!
            }
        }
    }
}