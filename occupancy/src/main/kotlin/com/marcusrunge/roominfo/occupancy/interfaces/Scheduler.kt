package com.marcusrunge.roominfo.occupancy.interfaces

interface Scheduler {
    var updateOccupancy: ((Int) -> Unit)?
    fun init()
}