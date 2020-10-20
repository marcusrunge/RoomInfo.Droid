package com.marcusrunge.roominfo.time.implementations

import android.content.Context
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.time.bases.TimeBase
import com.marcusrunge.roominfo.time.interfaces.Collision
import com.marcusrunge.roominfo.time.interfaces.DateTime

internal class TimeImpl(context: Context, data: Data) : TimeBase(context, data) {
    init {
        initTimeTick()
    }

    override val dateTime: DateTime
        get() = DateTimeFactoryImpl.createSingleton(this)
    override val collision: Collision
        get() = CollisionFactoryImpl.createSingleton(this)
}