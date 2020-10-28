package com.marcusrunge.roominfo.time.implementations

import com.marcusrunge.roominfo.time.bases.TimeBase
import com.marcusrunge.roominfo.time.interfaces.Timer

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
            //TODO
            _next?.invoke(occupancy)
        }
    }
}