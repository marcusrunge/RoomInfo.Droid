package com.marcusrunge.roominfo.data.implementations

import android.content.Context
import androidx.room.Room
import com.marcusrunge.roominfo.data.bases.TimeSpanItemsBase
import com.marcusrunge.roominfo.data.interfaces.TimeSpanItems
import com.marcusrunge.roominfo.data.interfaces.TimeSpanItemsFactory

internal class TimeSpanItemsFactoryImpl {
    companion object : TimeSpanItemsFactory {
        var timeSpanItems: TimeSpanItems? = null
        override fun createSingleton(context: Context): TimeSpanItems = when {
            timeSpanItems != null -> timeSpanItems!!
            else -> {
                timeSpanItems =
                    Room.databaseBuilder(context, TimeSpanItemsBase::class.java, "roominfo").build()
                        .timeSpanItems()
                timeSpanItems!!
            }
        }
    }
}