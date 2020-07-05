package com.marcusrunge.roominfo.data.implementations

import android.content.Context
import com.marcusrunge.roominfo.data.bases.DataBase
import com.marcusrunge.roominfo.data.interfaces.AgendaItems
import com.marcusrunge.roominfo.data.interfaces.TimeSpanItems

internal class DataImpl(context: Context) : DataBase(context) {
    override val agendaItems: AgendaItems
        get() = AgendaItemsFactoryImpl.createSingleton(this)
    override val timeSpanItems: TimeSpanItems
        get() = TimeSpanItemsFactoryImpl.createSingleton(this)
}