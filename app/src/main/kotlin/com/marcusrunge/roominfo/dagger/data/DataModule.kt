package com.marcusrunge.roominfo.dagger.data

import android.content.Context
import com.marcusrunge.roominfo.dagger.ApplicationContextModule
import com.marcusrunge.roominfo.data.implementations.DataFactoryImpl.Companion.createSingleton
import com.marcusrunge.roominfo.data.interfaces.Data
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationContextModule::class])
class DataModule {
    @Provides
    @Singleton
    fun provideData(context: Context): Data {
        return createSingleton(context)
    }
}