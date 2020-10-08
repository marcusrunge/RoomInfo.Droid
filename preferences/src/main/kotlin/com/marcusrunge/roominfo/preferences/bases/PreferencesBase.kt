package com.marcusrunge.roominfo.preferences.bases

import android.content.Context
import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import java.lang.ref.WeakReference

internal abstract class PreferencesBase(private val context: Context) : Preferences {
    private val onPreferenceChangedListener: MutableList<WeakReference<((Pair<String, Any?>) -> Unit)?>> =
        mutableListOf()

    override fun addOnPreferenceChangedListener(onTimeTickListener: ((Pair<String, Any?>) -> Unit)?) {
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
    }

    protected fun invokeOnPreferenceChangedListener(pair: Pair<String, Any?>) {
        for (weakRef in onPreferenceChangedListener) {
            try {
                weakRef.get()?.invoke(pair)
            } catch (e: Exception) {
            }
        }
    }
}