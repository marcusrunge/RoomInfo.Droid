package com.marcusrunge.roominfo.time.interfaces

interface Timer {
    val isStartSet: Boolean
    val isEndSet: Boolean
    var start: (() -> Unit)?
    var end: (() -> Unit)?
    fun setStart(start: Long)
    fun setEnd(end: Long)
    fun abort()
}