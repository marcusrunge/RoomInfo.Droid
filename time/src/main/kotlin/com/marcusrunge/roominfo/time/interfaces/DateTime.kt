package com.marcusrunge.roominfo.time.interfaces

interface DateTime {
    var timeUnit: ((String?) -> Unit)?
    var dateUnit: ((String?) -> Unit)?
}