package com.marcusrunge.roominfo.time.interfaces

import com.marcusrunge.roominfo.time.bases.TimeBase

internal interface CheckFindFactory {
    fun createSingleton(timeBase: TimeBase): CheckFind
}