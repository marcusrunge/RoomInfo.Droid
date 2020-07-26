package com.marcusrunge.roominfo.data.bases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marcusrunge.roominfo.data.interfaces.AgendaItems
import com.marcusrunge.roominfo.data.models.AgendaItem

@Database(entities = [AgendaItem::class], version = 1, exportSchema = true)
internal abstract class AgendaItemsBase : RoomDatabase() {
    abstract fun agendaItems(): AgendaItems
}