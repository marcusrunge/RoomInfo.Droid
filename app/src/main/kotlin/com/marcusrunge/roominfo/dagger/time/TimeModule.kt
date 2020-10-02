package com.marcusrunge.roominfo.dagger.time

import com.marcusrunge.roominfo.dagger.applicationresource.ApplicationResourceModule
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.time.implementations.TimeFactoryImpl
import com.marcusrunge.roominfo.time.interfaces.Time
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationResourceModule::class])
class TimeModule {
    @Provides
    @Singleton
    fun provideTime(applicationResource: ApplicationResource): Time {
        return TimeFactoryImpl.createSingleton(applicationResource.applicationContext!!)
    }
}