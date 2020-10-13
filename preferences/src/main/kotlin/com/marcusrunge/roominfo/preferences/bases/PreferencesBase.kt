package com.marcusrunge.roominfo.preferences.bases

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import java.lang.ref.WeakReference

internal abstract class PreferencesBase(context: Context) : Preferences,
    SharedPreferences.OnSharedPreferenceChangeListener {
    protected val sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
    private val onPreferenceChangedListener: MutableList<WeakReference<((Pair<String, Any?>) -> Unit)?>> =
        mutableListOf()

    override fun addOnPreferenceChangedListener(onTimeTickListener: ((Pair<String, Any?>) -> Unit)?) {
        if (this.onPreferenceChangedListener.size == 0) sharedPreferences.registerOnSharedPreferenceChangeListener(
            this
        )
        this.onPreferenceChangedListener.add(WeakReference(onTimeTickListener))
    }

    override fun removeOnPreferenceChangedListener(onPreferenceChangedListener: ((Pair<String, Any?>) -> Unit)?) {
        val iterator: MutableIterator<WeakReference<((Pair<String, Any?>) -> Unit)?>> =
            this.onPreferenceChangedListener.iterator()
        while (iterator.hasNext()) {
            val weakRef: WeakReference<((Pair<String, Any?>) -> Unit)?> = iterator.next()
            if (weakRef.get() === onPreferenceChangedListener) {
                iterator.remove()
            }
        }
        if (this.onPreferenceChangedListener.size == 0) sharedPreferences.unregisterOnSharedPreferenceChangeListener(
            this
        )
    }

    protected fun invokeOnPreferenceChangedListener(pair: Pair<String, Any?>) {
        for (weakRef in onPreferenceChangedListener) {
            try {
                weakRef.get()?.invoke(pair)
            } catch (e: Exception) {
            }
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {

        val value: Any? =
            when (sharedPreferences!!.all[key]?.javaClass) {
                Boolean::class.javaPrimitiveType -> invokeOnPreferenceChangedListener(
                    Pair(
                        key!!,
                        sharedPreferences.getBoolean(key, false)
                    )
                )
                Int::class.java -> sharedPreferences.getInt(key, 0)
                Long::class.java -> sharedPreferences.getLong(key, 0)
                Float::class.java -> sharedPreferences.getFloat(key, 0f)
                String::class.java -> sharedPreferences.getString(key, "")
                Set::class.java -> sharedPreferences.getStringSet(key, setOf<String>())
                else -> null
            }
        if (value != null) invokeOnPreferenceChangedListener(Pair(key!!, value))
    }
}