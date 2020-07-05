package com.marcusrunge.roominfo.data.implementations

import android.content.Context
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.data.interfaces.DataFactory

internal class DataFactoryImpl {
    companion object : DataFactory {
        var data: Data? = null
        override fun createSingleton(context: Context): Data = when {
            data != null -> data!!
            else -> {
                data = DataImpl(context)
                data!!
            }
        }
    }
}