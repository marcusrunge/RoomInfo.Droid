package com.marcusrunge.roominfo.occupancy.interfaces

interface Scheduler {
    var updateOccupancy: (() -> Unit)?
}