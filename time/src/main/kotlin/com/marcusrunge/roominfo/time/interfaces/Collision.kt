package com.marcusrunge.roominfo.time.interfaces

import java.time.LocalDateTime

interface Collision {
    val start: LocalDateTime
    val end: LocalDateTime
    fun checkStart(start: LocalDateTime): Boolean
    fun checkEnd(end: LocalDateTime): Boolean
}