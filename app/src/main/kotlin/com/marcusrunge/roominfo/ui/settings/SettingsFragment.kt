package com.marcusrunge.roominfo.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.marcusrunge.roominfo.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}