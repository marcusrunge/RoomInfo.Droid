package com.marcusrunge.roominfo.dagger

import android.content.Context
import com.marcusrunge.roominfo.RoomInfoApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationContextModule {
    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return RoomInfoApplication.context
    }
}