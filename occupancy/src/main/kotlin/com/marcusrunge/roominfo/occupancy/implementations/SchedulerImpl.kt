package com.marcusrunge.roominfo.occupancy.implementations

import com.marcusrunge.roominfo.occupancy.bases.OccupancyBase
import com.marcusrunge.roominfo.occupancy.interfaces.Scheduler
import java.lang.ref.WeakReference

internal class SchedulerImpl(occupancyBase: OccupancyBase) : Scheduler {
    private val updateOccupancyListener: MutableList<WeakReference<(() -> Unit)?>> =
        mutableListOf()

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

    override var updateOccupancy: (() -> Unit)? = null
        set(value) = when (value) {
            null -> removeUpdateOccupancyListener(value)
            else -> addUpdateOccupancyListener(value)
        }


    private fun addUpdateOccupancyListener(updateOccupancyListener: (() -> Unit)?) {
        this.updateOccupancyListener.add(WeakReference(updateOccupancyListener))
    }

    private fun removeUpdateOccupancyListener(updateOccupancyListener: (() -> Unit)?) {
        val iterator: MutableIterator<WeakReference<(() -> Unit)?>> =
            this.updateOccupancyListener.iterator()
        while (iterator.hasNext()) {
            val weakRef: WeakReference<(() -> Unit)?> = iterator.next()
            if (weakRef.get() === updateOccupancyListener) {
                iterator.remove()
            }
        }
    }

    private fun invokeUpdateOccupancy() {
        for (weakRef in updateOccupancyListener) {
            try {
                weakRef.get()?.invoke()
            } catch (e: Exception) {
            }
        }
    }
}