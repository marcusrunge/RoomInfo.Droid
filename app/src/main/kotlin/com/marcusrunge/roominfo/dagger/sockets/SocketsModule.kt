package com.marcusrunge.roominfo.dagger.sockets

import android.content.Context
import com.marcusrunge.roominfo.dagger.ApplicationContextModule
import com.marcusrunge.roominfo.sockets.implementations.SocketsFactoryImpl
import com.marcusrunge.roominfo.sockets.interfaces.Sockets
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationContextModule::class])
class SocketsModule {
    @Provides
    @Singleton
    fun provideSockets(context: Context): Sockets {
        return SocketsFactoryImpl.createSingleton(context)
    }
}