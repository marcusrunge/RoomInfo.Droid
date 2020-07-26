package com.marcusrunge.roominfo.data.bases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marcusrunge.roominfo.data.interfaces.TimeSpanItems
import com.marcusrunge.roominfo.data.models.TimeSpanItem

@Database(entities = [TimeSpanItem::class], version = 1, exportSchema = true)
internal abstract class TimeSpanItemsBase : RoomDatabase() {
    abstract fun timeSpanItems(): TimeSpanItems
}