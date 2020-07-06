package com.marcusrunge.roominfo.data.implementations

import android.content.Context
import androidx.room.Room
import com.marcusrunge.roominfo.data.bases.AgendaItemsBase
import com.marcusrunge.roominfo.data.interfaces.AgendaItems
import com.marcusrunge.roominfo.data.interfaces.AgendaItemsFactory

internal class AgendaItemsFactoryImpl {
    companion object : AgendaItemsFactory {
        var agendaItems: AgendaItems? = null
        override fun createSingleton(context: Context): AgendaItems = when {
            agendaItems != null -> agendaItems!!
            else -> {
                agendaItems =
                    Room.databaseBuilder(context, AgendaItemsBase::class.java, "roominfo").build()
                        .agendaItems()
                agendaItems!!
            }
        }
    }
}