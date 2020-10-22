package com.marcusrunge.roominfo.time.interfaces

import java.time.LocalDateTime

interface CheckFind {
    val end: LocalDateTime?
    fun findStart(selectedDate: LocalDateTime): LocalDateTime
    fun checkStart(start: LocalDateTime): Boolean
    fun checkEnd(end: LocalDateTime): Boolean
}