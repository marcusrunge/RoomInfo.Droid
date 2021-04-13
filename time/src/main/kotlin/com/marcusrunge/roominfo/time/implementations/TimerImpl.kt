package com.marcusrunge.roominfo.time.implementations

import com.marcusrunge.roominfo.data.models.AgendaItem
import com.marcusrunge.roominfo.data.models.TimeSpanItem
import com.marcusrunge.roominfo.time.bases.TimeBase
import com.marcusrunge.roominfo.time.interfaces.Timer
import java.time.OffsetDateTime

internal class TimerImpl(private val timeBase: TimeBase) : Timer {
    private var nextController: ((String?) -> Unit)? = findNextOccupancy()
    private var _next: ((occupancy: Int?) -> Unit)? = null

    override var next: ((occupancy: Int?) -> Unit)?
        get() = _next
        set(value) {
            if (value == null) timeBase.removeOnTimeTickListener(nextController)
            else {
                _next = value
                timeBase.addOnTimeTickListener(nextController)
            }
        }

    private fun findNextOccupancy(): ((String?) -> Unit)? {
        return {
            var occupancy: Int? = null
            val now = OffsetDateTime.now().toEpochSecond()
            var agendaItem: AgendaItem? = null
            var timeSpanItem: TimeSpanItem? = null
            val agendaItems = timeBase.data.agendaItems.getAll()
            val timeSpanItems = timeBase.data.timeSpanItems.getAll()
            agendaItems.iterator().forEach {
                if (it.Start!! >= now) {
                    if (agendaItem == null) agendaItem = it
                    else if (agendaItem!!.Start!! > it.Start!!) agendaItem = it
                }
            }
            timeSpanItems.iterator().forEach {
                if (it.Start!! >= now) {
                    if (timeSpanItem == null) timeSpanItem = it
                    else if (timeSpanItem!!.Start!! > it.Start!!) timeSpanItem = it
                }
            }
            when {
                agendaItem != null -> {
                    if (timeSpanItem != null && agendaItem!!.Start!! > timeSpanItem!!.Start!!) {
                        occupancy = timeSpanItem!!.Occupancy
                    }
                }
                timeSpanItem != null -> {
                    occupancy = timeSpanItem!!.Occupancy
                }
                else -> {
                    occupancy = timeBase.preferences.standardOccupancy
                }
            }
            _next?.invoke(occupancy)
        }
    }
}