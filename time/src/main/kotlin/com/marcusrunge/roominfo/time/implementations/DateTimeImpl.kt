package com.marcusrunge.roominfo.time.implementations

import com.marcusrunge.roominfo.time.bases.TimeBase
import com.marcusrunge.roominfo.time.interfaces.DateTime

internal class DateTimeImpl(private val timeBase: TimeBase) : DateTime {
    private var _timeUnit: ((String?) -> Unit)? = null
    override var timeUnit: ((String?) -> Unit)?
        get() = _timeUnit
        set(value) {
            if (value == null) timeBase.removeOnTimeTickListener(_timeUnit)
            else {
                _timeUnit = value
                timeBase.addOnTimeTickListener(_timeUnit)
            }
        }

    private var _dateUnit: ((String?) -> Unit)? = null
    override var dateUnit: ((String?) -> Unit)?
        get() = _dateUnit
        set(value) {
            if (value == null) timeBase.removeOnDateTickListener(_dateUnit)
            else {
                _dateUnit = value
                timeBase.addOnDateTickListener(_dateUnit)
            }
        }
}