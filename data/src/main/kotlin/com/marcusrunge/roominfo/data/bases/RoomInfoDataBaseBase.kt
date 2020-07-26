package com.marcusrunge.roominfo.data.bases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marcusrunge.roominfo.data.interfaces.AgendaItems
import com.marcusrunge.roominfo.data.interfaces.TimeSpanItems
import com.marcusrunge.roominfo.data.models.AgendaItem
import com.marcusrunge.roominfo.data.models.TimeSpanItem

@Database(entities = [AgendaItem::class, TimeSpanItem::class], version = 1, exportSchema = true)
internal abstract class RoomInfoDataBaseBase : RoomDatabase() {
    abstract fun agendaItems(): AgendaItems
    abstract fun timeSpanItems(): TimeSpanItems
}