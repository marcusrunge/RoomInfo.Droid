package com.marcusrunge.roominfo.time.interfaces

import java.time.LocalDateTime

interface CheckFind {
    val end: LocalDateTime?
    suspend fun findStart(selectedDate: LocalDateTime): LocalDateTime
    suspend fun checkStart(start: LocalDateTime): Boolean
    suspend fun checkEnd(end: LocalDateTime): Boolean
}