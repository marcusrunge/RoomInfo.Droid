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
    override val roomDesignator: String?
        get() = sharedPreferences.getString("roomDesignator", null)
    override val udpPort: String?
        get() = sharedPreferences.getString("udpPort", null)
    override val tcpPort: String?
        get() = sharedPreferences.getString("tcpPort", null)
    override val logoFilePath: String?
        get() = sharedPreferences.getString("logoFilePath", null)
}