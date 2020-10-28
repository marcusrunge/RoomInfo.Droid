package com.marcusrunge.roominfo.time.interfaces

interface Timer {
    var next: ((occupancy: Int?) -> Unit)?
}