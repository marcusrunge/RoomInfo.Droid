package com.marcusrunge.roominfo.time.implementations

import com.marcusrunge.roominfo.time.bases.TimeBase
import com.marcusrunge.roominfo.time.interfaces.CheckFind
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.OffsetDateTime

internal class CheckFindImpl(private val timeBase: TimeBase) : CheckFind {
    private lateinit var window: Pair<LocalDateTime, LocalDateTime>
    private val offset = OffsetDateTime.now().offset

    override val end: LocalDateTime?
        get() = if (::window.isInitialized) window.second else null

    override suspend fun findStart(selectedDate: LocalDateTime): LocalDateTime {
        return withContext(Dispatchers.IO) {
            val selectedDateAsEpochSecond = selectedDate.toEpochSecond(offset)
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
            val agendaItems = timeBase.data.agendaItems.getAll(
                selectedDateAsEpochSecond,
                selectedDateAsEpochSecond + 86400
            )
            for (x in agendaItems.indices) {
                if (agendaItems[x].Start!! >= window.first.toEpochSecond(offset) && agendaItems[x].Start!! <= window.second.toEpochSecond(
                        offset
                    )
                ) {
                    if (x == agendaItems.size - 1 || agendaItems[x + 1].Start!! > agendaItems[x].End!! + 1860) {
                        window = Pair(
                            LocalDateTime.ofEpochSecond(agendaItems[x].End!! + 60, 0, offset),
                            LocalDateTime.ofEpochSecond(agendaItems[x].End!! + 1860, 0, offset)
                        )
                    } else if (agendaItems[x + 1].Start!! > agendaItems[x].End!! + 60) {
                        window = Pair(
                            LocalDateTime.ofEpochSecond(agendaItems[x].End!! + 60, 0, offset),
                            LocalDateTime.ofEpochSecond(agendaItems[x + 1].Start!! - 60, 0, offset)
                        )
                    }
                }
            }
            window.first
        }
    }

    override suspend fun checkStart(start: LocalDateTime): Boolean {
        return withContext(Dispatchers.IO) {
            timeBase.data.agendaItems.getAll().forEach {
                if (start.toEpochSecond(offset) >= it.Start!! && start.toEpochSecond(offset) <= it.End!!) false
            }
            true
        }
    }

    override suspend fun checkEnd(end: LocalDateTime): Boolean {
        return withContext(Dispatchers.IO) {
            timeBase.data.agendaItems.getAll().forEach {
                if (end.toEpochSecond(offset) >= it.Start!!) false
            }
            true
        }
    }
}