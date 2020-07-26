package com.marcusrunge.roominfo.data.bases

import android.content.Context
import androidx.room.Room
import com.marcusrunge.roominfo.data.interfaces.Data

internal abstract class DataBase(context: Context) : Data {
    protected val roomInfoItemsBase: RoomInfoDataBaseBase =
        Room.databaseBuilder(context, RoomInfoDataBaseBase::class.java, "roominfo").build()
}