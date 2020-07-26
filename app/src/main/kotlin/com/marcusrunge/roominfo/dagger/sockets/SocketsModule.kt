package com.marcusrunge.roominfo.dagger.sockets

import com.marcusrunge.roominfo.dagger.applicationresource.ApplicationResourceModule
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.sockets.implementations.SocketsFactoryImpl
import com.marcusrunge.roominfo.sockets.interfaces.Sockets
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationResourceModule::class])
class SocketsModule {
    @Provides
    @Singleton
    fun provideSockets(applicationResource: ApplicationResource): Sockets {
        return SocketsFactoryImpl.createSingleton(applicationResource.applicationContext!!)
    }
}