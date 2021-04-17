package com.marcusrunge.roominfo.occupancy.implementations

import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.occupancy.bases.OccupancyBase
import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import com.marcusrunge.roominfo.time.interfaces.Time

internal class OccupancyImpl(time: Time, data: Data, preferences: Preferences) :
    OccupancyBase(time, data, preferences)