package com.marcusrunge.roominfo.time.implementations

import android.content.Context
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import com.marcusrunge.roominfo.time.bases.TimeBase
import com.marcusrunge.roominfo.time.interfaces.CheckFind
import com.marcusrunge.roominfo.time.interfaces.DateTime
import com.marcusrunge.roominfo.time.interfaces.Timer

internal class TimeImpl(context: Context, data: Data, preferences: Preferences) :
    TimeBase(context, data, preferences) {
    init {
        initTimeTick()
    }

    override val dateTime: DateTime
        get() = DateTimeFactoryImpl.createSingleton(this)
    override val checkFind: CheckFind
        get() = CheckFindFactoryImpl.createSingleton(this)
    override val timer: Timer
        get() = TimerFactoryImpl.createSingleton(this)
}