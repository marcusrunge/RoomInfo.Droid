package com.marcusrunge.roominfo.data.implementations

import com.marcusrunge.roominfo.data.bases.DataBase
import com.marcusrunge.roominfo.data.interfaces.TimeSpanItems
import com.marcusrunge.roominfo.data.interfaces.TimeSpanItemsFactory

internal class TimeSpanItemsFactoryImpl {
    companion object : TimeSpanItemsFactory {
        var timeSpanItems: TimeSpanItems? = null
        override fun createSingleton(dataBase: DataBase): TimeSpanItems = when {
            timeSpanItems != null -> timeSpanItems!!
            else -> {
                timeSpanItems = TimeSpanItemsImpl(dataBase)
                timeSpanItems!!
            }
        }
    }
}