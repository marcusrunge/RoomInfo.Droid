package com.marcusrunge.roominfo.time.implementations

import com.marcusrunge.roominfo.time.bases.TimeBase
import com.marcusrunge.roominfo.time.interfaces.DateTime

internal class DateTimeImpl(private val timeBase: TimeBase) : DateTime {
    override val time: String?
        get() = TODO("Not yet implemented")
    override val date: String?
        get() = TODO("Not yet implemented")
}