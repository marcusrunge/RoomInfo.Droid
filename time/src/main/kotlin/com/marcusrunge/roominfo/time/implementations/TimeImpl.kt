package com.marcusrunge.roominfo.time.implementations

import android.content.Context
import com.marcusrunge.roominfo.time.bases.TimeBase
import com.marcusrunge.roominfo.time.interfaces.DateTime

internal class TimeImpl(context: Context) : TimeBase(context) {
    init {
        initTimeTick()
    }

    override val dateTime: DateTime
        get() = DateTimeFactoryImpl.createSingleton(this)
}