package com.marcusrunge.roominfo.occupancy.bases

import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.occupancy.interfaces.Occupancy
import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import com.marcusrunge.roominfo.time.interfaces.Time

internal abstract class OccupancyBase(
    val time: Time,
    val data: Data,
    val preferences: Preferences
) : Occupancy