package com.marcusrunge.roominfo.time.interfaces

import com.marcusrunge.roominfo.time.bases.TimeBase

internal interface TimerFactory {
    fun createSingleton(timeBase: TimeBase): Timer
}