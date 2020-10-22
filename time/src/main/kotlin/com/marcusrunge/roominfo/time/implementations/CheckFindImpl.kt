package com.marcusrunge.roominfo.time.implementations

import com.marcusrunge.roominfo.time.bases.TimeBase
import com.marcusrunge.roominfo.time.interfaces.CheckFind
import java.time.LocalDateTime
import java.time.OffsetDateTime

internal class CheckFindImpl(private val timeBase: TimeBase) : CheckFind {
    private lateinit var window: Pair<LocalDateTime, LocalDateTime>

    override val end: LocalDateTime?
        get() = if (::window.isInitialized) window.second else null

    override fun findStart(selectedDate: LocalDateTime): LocalDateTime {
        val selectedDateAsEpochSecond = selectedDate.toEpochSecond(OffsetDateTime.now().offset)
        window = Pair<LocalDateTime, LocalDateTime>(
            LocalDateTime.of(
                selectedDate.year,
                selectedDate.month,
                selectedDate.dayOfMonth,
                0,
                0
            ), LocalDateTime.of(
                selectedDate.year,
                selectedDate.month,
                selectedDate.dayOfMonth,
                0,
                30
            )
        )
        timeBase.data.agendaItems.getAll(
            selectedDateAsEpochSecond,
            selectedDateAsEpochSecond + 86400
        ).forEach {
            //TODO
        }
        return window.first
    }

    override fun checkStart(start: LocalDateTime): Boolean {
        TODO("Not yet implemented")
    }

    override fun checkEnd(end: LocalDateTime): Boolean {
        TODO("Not yet implemented")
    }
}