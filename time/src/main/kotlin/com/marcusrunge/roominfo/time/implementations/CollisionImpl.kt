package com.marcusrunge.roominfo.time.implementations

import com.marcusrunge.roominfo.time.bases.TimeBase
import com.marcusrunge.roominfo.time.interfaces.Collision
import java.time.LocalDateTime

internal class CollisionImpl(private val timeBase: TimeBase) : Collision {
    override fun findStart(selectedDate: LocalDateTime): LocalDateTime {
        TODO("Not yet implemented")
    }

    override fun findEnd(selectedDate: LocalDateTime): LocalDateTime {
        TODO("Not yet implemented")
    }

    override fun checkStart(start: LocalDateTime): Boolean {
        TODO("Not yet implemented")
    }

    override fun checkEnd(end: LocalDateTime): Boolean {
        TODO("Not yet implemented")
    }
}