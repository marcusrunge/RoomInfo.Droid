package com.marcusrunge.roominfo.sockets.interfaces

import android.content.Context

interface SocketsFactory {
    fun createSingleton(context: Context): Sockets
}