package com.marcusrunge.roominfo.data.implementations

import android.content.Context
import com.marcusrunge.roominfo.data.bases.DataBase
import com.marcusrunge.roominfo.data.interfaces.AgendaItems
import com.marcusrunge.roominfo.data.interfaces.TimeSpanItems

internal class DataImpl(context: Context) : DataBase(context) {
    override val agendaItems: AgendaItems
        get() = roomInfoItemsBase.agendaItems()
    override val timeSpanItems: TimeSpanItems
        get() = roomInfoItemsBase.timeSpanItems()

    override fun close() {
        roomInfoItemsBase.close()
    }
}