package com.marcusrunge.roominfo.preferences.interfaces

interface Preferences {
    val companyName: String?
    val roomDesignator: String?
    val udpPort: String?
    val tcpPort: String?
    var logoFilePath: String?
}