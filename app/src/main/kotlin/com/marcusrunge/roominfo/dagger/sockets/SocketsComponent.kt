package com.marcusrunge.roominfo.dagger.sockets

import com.marcusrunge.roominfo.sockets.interfaces.Sockets
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SocketsModule::class])
interface SocketsComponent {
    fun provideSockets(): Sockets
}