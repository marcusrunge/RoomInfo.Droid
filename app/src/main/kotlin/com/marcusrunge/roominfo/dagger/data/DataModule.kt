package com.marcusrunge.roominfo.dagger.data

import com.marcusrunge.roominfo.dagger.applicationresource.ApplicationResourceModule
import com.marcusrunge.roominfo.data.implementations.DataFactoryImpl.Companion.createSingleton
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.models.ApplicationResource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationResourceModule::class])
class DataModule {
    @Provides
    @Singleton
    fun provideData(applicationResource: ApplicationResource): Data {
        return createSingleton(applicationResource.applicationContext!!)
    }
}