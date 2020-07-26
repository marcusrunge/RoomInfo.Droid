package com.marcusrunge.roominfo.dagger.applicationresource

import com.marcusrunge.roominfo.RoomInfoApplication
import com.marcusrunge.roominfo.models.ApplicationResource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationResourceModule {
    @Singleton
    @Provides
    fun provideApplicationResource(): ApplicationResource {
        return RoomInfoApplication.applicationResource
    }
}