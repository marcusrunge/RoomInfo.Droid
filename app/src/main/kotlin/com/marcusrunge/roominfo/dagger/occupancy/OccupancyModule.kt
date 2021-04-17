package com.marcusrunge.roominfo.dagger.occupancy

import com.marcusrunge.roominfo.dagger.data.DataModule
import com.marcusrunge.roominfo.dagger.preferences.PreferencesModule
import com.marcusrunge.roominfo.dagger.time.TimeModule
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.occupancy.implementations.OccupancyFactoryImpl
import com.marcusrunge.roominfo.occupancy.interfaces.Occupancy
import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import com.marcusrunge.roominfo.time.interfaces.Time
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [TimeModule::class, DataModule::class, PreferencesModule::class])
class OccupancyModule {
    @Provides
    @Singleton
    fun provideOccupancy(
        time: Time,
        data: Data,
        preferences: Preferences
    ): Occupancy {
        return OccupancyFactoryImpl.createSingleton(
            time,
            data,
            preferences
        )
    }
}