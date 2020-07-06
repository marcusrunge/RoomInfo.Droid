package com.marcusrunge.roominfo.data.interfaces

import android.content.Context

internal interface AgendaItemsFactory {
    fun createSingleton(context: Context): AgendaItems
}