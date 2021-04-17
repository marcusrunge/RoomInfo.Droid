package com.marcusrunge.roominfo.occupancy.interfaces

import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import com.marcusrunge.roominfo.time.interfaces.Time

interface OccupancyFactory {
    fun createSingleton(time: Time, data: Data, preferences: Preferences): Occupancy
}