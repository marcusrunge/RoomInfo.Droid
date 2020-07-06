package com.marcusrunge.roominfo.data.implementations

import android.content.Context
import com.marcusrunge.roominfo.data.interfaces.AgendaItems
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.data.interfaces.TimeSpanItems

internal class DataImpl(private val context: Context) : Data {
    override val agendaItems: AgendaItems
        get() = AgendaItemsFactoryImpl.createSingleton(context)
    override val timeSpanItems: TimeSpanItems
        get() = TimeSpanItemsFactoryImpl.createSingleton(context)
}