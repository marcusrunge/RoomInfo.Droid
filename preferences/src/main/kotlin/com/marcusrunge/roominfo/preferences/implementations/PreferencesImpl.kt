package com.marcusrunge.roominfo.preferences.implementations

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.marcusrunge.roominfo.preferences.bases.PreferencesBase

internal class PreferencesImpl(context: Context) : PreferencesBase(context) {
    private val sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    override val companyName: String?
        get() = sharedPreferences.getString("companyName", null)
    override val roomName: String?
        get() = sharedPreferences.getString("roomName", null)
    override val roomNumber: String?
        get() = sharedPreferences.getString("roomNumber", null)
    override val udpPort: String?
        get() = sharedPreferences.getString("udpPort", null)
    override val tcpPort: String?
        get() = sharedPreferences.getString("tcpPort", null)
    override var logoFilePath: String?
        get() = sharedPreferences.getString("logoFilePath", null)
        set(value) {
            sharedPreferences.edit()?.putString("logoFilePath", value)?.apply()
        }
    override var occupancy: Int?
        get() = sharedPreferences.getInt("occupancy", 0)
        set(value) {
            sharedPreferences.edit()?.putInt("occupancy", value!!)?.apply()
        }
    override val standardOccupancy: Int?
        get() = Integer.parseInt(sharedPreferences.getString("standardOccupancy", "0")!!)
}