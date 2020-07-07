package com.marcusrunge.roominfo.data.bases

import android.content.Context
import androidx.room.Room
import com.marcusrunge.roominfo.data.interfaces.Data

internal abstract class DataBase(context: Context) : Data {
    protected val timeSpanItemsBase: TimeSpanItemsBase =
        Room.databaseBuilder(context, TimeSpanItemsBase::class.java, "roominfo").build()
    protected val agendaItemsBase: AgendaItemsBase =
        Room.databaseBuilder(context, AgendaItemsBase::class.java, "roominfo").build()
}