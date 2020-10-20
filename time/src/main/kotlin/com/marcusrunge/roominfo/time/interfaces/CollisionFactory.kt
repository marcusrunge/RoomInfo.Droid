package com.marcusrunge.roominfo.time.interfaces

import com.marcusrunge.roominfo.time.bases.TimeBase

internal interface CollisionFactory {
    fun createSingleton(timeBase: TimeBase): Collision
}