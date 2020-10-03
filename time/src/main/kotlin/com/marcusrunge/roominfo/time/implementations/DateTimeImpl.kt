package com.marcusrunge.roominfo.time.implementations

import com.marcusrunge.roominfo.time.bases.TimeBase
import com.marcusrunge.roominfo.time.interfaces.DateTime

internal class DateTimeImpl(private val timeBase: TimeBase) : DateTime {
    override var timeUnit: Unit? = null
        set(value) {
            if (value == null && field != null) {
                timeBase.removeOnTimeTickListener { field }
            } else field = timeBase.addOnTimeTickUnit { value }
        }
    override var dateUnit: Unit? = null
        set(value) {
            if (value == null && field != null) {
                timeBase.removeOnTimeTickListener { field }
            } else field = timeBase.addOnTimeTickUnit { value }
        }
}