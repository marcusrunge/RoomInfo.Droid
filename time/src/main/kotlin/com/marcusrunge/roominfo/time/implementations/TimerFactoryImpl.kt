package com.marcusrunge.roominfo.time.implementations

import com.marcusrunge.roominfo.time.bases.TimeBase
import com.marcusrunge.roominfo.time.interfaces.Timer
import com.marcusrunge.roominfo.time.interfaces.TimerFactory

internal class TimerFactoryImpl {
    companion object : TimerFactory {
        var timer: Timer? = null
        override fun createSingleton(timeBase: TimeBase): Timer = when {
            timer != null -> timer!!
            else -> {
                timer = TimerImpl(timeBase)
                timer!!
            }
        }
    }
}