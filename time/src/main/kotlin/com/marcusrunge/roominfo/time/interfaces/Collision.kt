package com.marcusrunge.roominfo.time.interfaces

import java.time.LocalDateTime

interface Collision {
    fun findStart(selectedDate: LocalDateTime): LocalDateTime
    fun findEnd(selectedDate: LocalDateTime): LocalDateTime
    fun checkStart(start: LocalDateTime): Boolean
    fun checkEnd(end: LocalDateTime): Boolean
}