package com.marcusrunge.roominfo.data.interfaces

import android.content.Context

internal interface TimeSpanItemsFactory {
    fun createSingleton(context: Context): TimeSpanItems
}