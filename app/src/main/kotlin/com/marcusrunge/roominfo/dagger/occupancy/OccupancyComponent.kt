package com.marcusrunge.roominfo.dagger.occupancy

import com.marcusrunge.roominfo.occupancy.interfaces.Occupancy
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [OccupancyModule::class])
interface OccupancyComponent {
    fun provideOccupancy(): Occupancy
}