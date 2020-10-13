package com.marcusrunge.roominfo.preferences.interfaces

interface Preferences {
    val companyName: String?
    val roomName: String?
    val roomNumber: String?
    val udpPort: String?
    val tcpPort: String?
    var logoFilePath: String?
    var occupancy: Int?
    val standardOccupancy: Int?
    val theme: Int?
    fun addOnPreferenceChangedListener(onTimeTickListener: ((Pair<String, Any?>) -> Unit)?)
    fun removeOnPreferenceChangedListener(onPreferenceChangedListener: ((Pair<String, Any?>) -> Unit)?)
}