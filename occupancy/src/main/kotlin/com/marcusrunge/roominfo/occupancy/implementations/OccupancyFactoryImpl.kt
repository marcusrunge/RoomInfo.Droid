package com.marcusrunge.roominfo.occupancy.implementations

import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.occupancy.interfaces.Occupancy
import com.marcusrunge.roominfo.occupancy.interfaces.OccupancyFactory
import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import com.marcusrunge.roominfo.time.interfaces.Time

class OccupancyFactoryImpl {
    companion object : OccupancyFactory {
        var occupancy: Occupancy? = null
        override fun createSingleton(time: Time, data: Data, preferences: Preferences): Occupancy =
            when {
                occupancy != null -> occupancy!!
                else -> {
                    occupancy = OccupancyImpl(time, data, preferences)
                    occupancy!!
                }
            }
    }
}