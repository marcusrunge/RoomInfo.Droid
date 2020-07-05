package com.marcusrunge.roominfo.data.interfaces

import com.marcusrunge.roominfo.data.bases.DataBase

internal interface TimeSpanItemsFactory {
    fun createSingleton(dataBase: DataBase): TimeSpanItems
}