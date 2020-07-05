package com.marcusrunge.roominfo.data.implementations

import com.marcusrunge.roominfo.data.bases.DataBase
import com.marcusrunge.roominfo.data.interfaces.AgendaItems
import com.marcusrunge.roominfo.data.interfaces.AgendaItemsFactory

internal class AgendaItemsFactoryImpl {
    companion object : AgendaItemsFactory {
        var agendaItems: AgendaItems? = null
        override fun createSingleton(dataBase: DataBase): AgendaItems = when {
            agendaItems != null -> agendaItems!!
            else -> {
                agendaItems = AgendaItemsImpl(dataBase)
                agendaItems!!
            }
        }
    }
}