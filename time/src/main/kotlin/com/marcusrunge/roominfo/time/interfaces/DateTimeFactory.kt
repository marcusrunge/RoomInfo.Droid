package com.marcusrunge.roominfo.time.interfaces

import com.marcusrunge.roominfo.time.bases.TimeBase

internal interface DateTimeFactory {
    fun createSingleton(timeBase: TimeBase): DateTime
}