package com.marcusrunge.roominfo.occupancy.implementations

import com.marcusrunge.roominfo.data.models.AgendaItem
import com.marcusrunge.roominfo.data.models.TimeSpanItem
import com.marcusrunge.roominfo.occupancy.bases.OccupancyBase
import com.marcusrunge.roominfo.occupancy.interfaces.Scheduler
import java.lang.ref.WeakReference
import java.time.OffsetDateTime

internal class SchedulerImpl(private val occupancyBase: OccupancyBase) : Scheduler {
    private val updateOccupancyListener: MutableList<WeakReference<((Int) -> Unit)?>> =
        mutableListOf()

    private var occupancy: Int? = null

    internal companion object {
        var scheduler: Scheduler? = null
        internal fun singleInstance(occupancyBase: OccupancyBase): Scheduler = when {
            scheduler != null -> scheduler!!
            else -> {
                scheduler = SchedulerImpl(occupancyBase)
                scheduler!!
            }
        }
    }

    init {
        occupancyBase.time.timer.start = { occupancy?.let { invokeUpdateOccupancy(it) } }
        occupancyBase.time.timer.end = { setNextOccupancy() }
        setNextOccupancy()
    }

    private fun setNextOccupancy() {
        val now = OffsetDateTime.now().toEpochSecond()
        var nextAgendaItem: AgendaItem? = null
        var nextTimeSpanItem: TimeSpanItem? = null
        var currentAgendaItem: AgendaItem? = null
        var currentTimeSpanItem: TimeSpanItem? = null
        val agendaItems = occupancyBase.data.agendaItems.getAll()
        val timeSpanItems = occupancyBase.data.timeSpanItems.getAll()
        agendaItems.iterator().forEach {
            if (it.Start!! >= now) {
                if (nextAgendaItem == null) nextAgendaItem = it
                else if (nextAgendaItem!!.Start!! > it.Start!!) nextAgendaItem = it
            } else if (it.End!! >= now) {
                currentAgendaItem = it
            }
        }
        timeSpanItems.iterator().forEach {
            if (it.Start!! >= now) {
                if (nextTimeSpanItem == null) nextTimeSpanItem = it
                else if (nextTimeSpanItem!!.Start!! > it.Start!!) nextTimeSpanItem = it
            } else if (it.End!! >= now) {
                currentTimeSpanItem = it
            }
        }
        when {
            currentAgendaItem != null -> {
                invokeUpdateOccupancy(currentAgendaItem!!.Occupancy!!)
            }
            nextAgendaItem != null -> {
                invokeUpdateOccupancy(occupancyBase.preferences.standardOccupancy!!)
                occupancyBase.time.timer.setStart(nextAgendaItem!!.Start!!)
                occupancyBase.time.timer.setEnd(nextAgendaItem!!.End!!)
            }
            currentTimeSpanItem != null -> {
                invokeUpdateOccupancy(currentTimeSpanItem!!.Occupancy!!)
            }
            nextTimeSpanItem != null -> {
                invokeUpdateOccupancy(occupancyBase.preferences.standardOccupancy!!)
                occupancyBase.time.timer.setStart(nextTimeSpanItem!!.Start!!)
                occupancyBase.time.timer.setEnd(nextTimeSpanItem!!.End!!)
            }
            else -> {
                invokeUpdateOccupancy(occupancyBase.preferences.standardOccupancy!!)
            }
        }
    }

    override var updateOccupancy: ((Int) -> Unit)? = null
        set(value) = when (value) {
            null -> removeUpdateOccupancyListener(value)
            else -> addUpdateOccupancyListener(value)
        }


    private fun addUpdateOccupancyListener(updateOccupancyListener: ((Int) -> Unit)?) {
        this.updateOccupancyListener.add(WeakReference(updateOccupancyListener))
    }

    private fun removeUpdateOccupancyListener(updateOccupancyListener: (() -> Unit)?) {
        val iterator: MutableIterator<WeakReference<((Int) -> Unit)?>> =
            this.updateOccupancyListener.iterator()
        while (iterator.hasNext()) {
            val weakRef: WeakReference<((Int) -> Unit)?> = iterator.next()
            if (weakRef.get() === updateOccupancyListener) {
                iterator.remove()
            }
        }
    }

    private fun invokeUpdateOccupancy(occupancy: Int) {
        for (weakRef in updateOccupancyListener) {
            try {
                weakRef.get()?.invoke(occupancy)
            } catch (e: Exception) {
            }
        }
    }
}