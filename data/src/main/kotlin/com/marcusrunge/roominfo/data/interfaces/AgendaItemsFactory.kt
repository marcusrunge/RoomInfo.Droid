package com.marcusrunge.roominfo.data.interfaces

import com.marcusrunge.roominfo.data.bases.DataBase

internal interface AgendaItemsFactory {
    fun createSingleton(dataBase: DataBase): AgendaItems
}