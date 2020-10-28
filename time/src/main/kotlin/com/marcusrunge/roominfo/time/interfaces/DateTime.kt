package com.marcusrunge.roominfo.time.interfaces

interface DateTime {
    var timing: ((String?) -> Unit)?
    var dating: ((String?) -> Unit)?
}