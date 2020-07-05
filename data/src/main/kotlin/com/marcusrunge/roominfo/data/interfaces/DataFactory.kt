package com.marcusrunge.roominfo.data.interfaces

import android.content.Context

interface DataFactory {
    fun createSingleton(context: Context): Data
}