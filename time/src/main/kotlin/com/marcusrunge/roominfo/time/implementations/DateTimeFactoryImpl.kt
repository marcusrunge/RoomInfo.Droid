package com.marcusrunge.roominfo.time.implementations

import com.marcusrunge.roominfo.time.bases.TimeBase
import com.marcusrunge.roominfo.time.interfaces.DateTime
import com.marcusrunge.roominfo.time.interfaces.DateTimeFactory

internal class DateTimeFactoryImpl {
    companion object : DateTimeFactory {
        var dateTime: DateTime? = null
        override fun createSingleton(timeBase: TimeBase): DateTime = when {
            dateTime != null -> dateTime!!
            else -> {
                dateTime = DateTimeImpl(timeBase)
                dateTime!!
            }
        }
    }
}