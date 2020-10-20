package com.marcusrunge.roominfo.time.implementations

import com.marcusrunge.roominfo.time.bases.TimeBase
import com.marcusrunge.roominfo.time.interfaces.Collision
import com.marcusrunge.roominfo.time.interfaces.CollisionFactory

internal class CollisionFactoryImpl {
    companion object : CollisionFactory {
        var collision: Collision? = null
        override fun createSingleton(timeBase: TimeBase): Collision = when {
            collision != null -> collision!!
            else -> {
                collision = CollisionImpl(timeBase)
                collision!!
            }
        }
    }
}