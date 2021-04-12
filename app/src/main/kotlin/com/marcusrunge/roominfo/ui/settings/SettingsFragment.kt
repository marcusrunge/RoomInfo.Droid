package com.marcusrunge.roominfo.ui.settings

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.marcusrunge.roominfo.MainActivity
import com.marcusrunge.roominfo.R
import com.marcusrunge.roominfo.interfaces.OnFileSelectedListener
import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import dagger.android.support.AndroidSupportInjection
import java.io.InputStream
import javax.inject.Inject

class SettingsFragment : PreferenceFragmentCompat(), OnFileSelectedListener {

    @Inject
    lateinit var preferences: Preferences

    @Override
    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).addOnFileSelectedListener(this)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        val logoPicker = findPreference<Preference>("logoPicker")
        logoPicker?.setOnPreferenceClickListener {
            (activity as MainActivity).getContent.launch("image/*")
            true
        }
        if (preferences.logoFilePath != null) {
            val fileUri = Uri.parse(preferences.logoFilePath!!)
            val inputStream: InputStream? = activity?.contentResolver?.openInputStream(fileUri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            logoPicker?.icon =
                Icon.createWithBitmap(bitmap).loadDrawable(
                    context
                )
        }
    }

    override fun onFileSelected(fileUri: Uri?) {
        if (fileUri != null) {
            val logoPicker = findPreference<Preference>("logoPicker")
            val inputStream: InputStream? = activity?.contentResolver?.openInputStream(fileUri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            logoPicker?.icon =
                Icon.createWithBitmap(bitmap).loadDrawable(
                    context
                )
            preferences.logoFilePath = fileUri.toString()
        }
    }

    override fun onDestroy() {
        (activity as MainActivity).removeOnFileSelectedListener(this)
        super.onDestroy()
    }
}