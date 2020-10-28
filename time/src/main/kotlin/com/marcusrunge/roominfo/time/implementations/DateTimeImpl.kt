package com.marcusrunge.roominfo.time.implementations

import com.marcusrunge.roominfo.time.bases.TimeBase
import com.marcusrunge.roominfo.time.interfaces.DateTime

internal class DateTimeImpl(private val timeBase: TimeBase) : DateTime {
    private var _timing: ((String?) -> Unit)? = null
    override var timing: ((String?) -> Unit)?
        get() = _timing
        set(value) {
            if (value == null) timeBase.removeOnTimeTickListener(_timing)
            else {
                _timing = value
                timeBase.addOnTimeTickListener(_timing)
            }
        }

    private var _dating: ((String?) -> Unit)? = null
    override var dating: ((String?) -> Unit)?
        get() = _dating
        set(value) {
            if (value == null) timeBase.removeOnDateTickListener(_dating)
            else {
                _dating = value
                timeBase.addOnDateTickListener(_dating)
            }
        }
}