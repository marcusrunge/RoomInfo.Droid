package com.marcusrunge.roominfo.sockets.implementations

import android.content.Context
import com.marcusrunge.roominfo.sockets.interfaces.Sockets
import com.marcusrunge.roominfo.sockets.interfaces.SocketsFactory

class SocketsFactoryImpl {
    companion object : SocketsFactory {
        var sockets: Sockets? = null
        override fun createSingleton(context: Context): Sockets = when {
            sockets != null -> sockets!!
            else -> {
                sockets = SocketsImpl(context)
                sockets!!
            }
        }
    }
}