package com.marcusrunge.roominfo.time.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.lang.ref.WeakReference

class TimeTickReceiver : BroadcastReceiver() {
    private val onTimeTickListeners: MutableList<WeakReference<OnTimeTickListener>> =
        mutableListOf()

    override fun onReceive(context: Context, intent: Intent) {
        invokeOnTimeTickListener()
    }

    fun addOnTimeTickListener(onTimeTickListener: OnTimeTickListener) {
        onTimeTickListeners.add(WeakReference(onTimeTickListener))
    }

    fun removeOnTimeTickListener(onTimeTickListener: OnTimeTickListener) {
        val iterator: MutableIterator<WeakReference<OnTimeTickListener>> =
            onTimeTickListeners.iterator()
        while (iterator.hasNext()) {
            val weakRef: WeakReference<OnTimeTickListener> = iterator.next()
            if (weakRef.get() === onTimeTickListener) {
                iterator.remove()
            }
        }
    }

    private fun invokeOnTimeTickListener() {
        val currentTimeMillis = System.currentTimeMillis()
        for (weakRef in onTimeTickListeners) {
            try {
                weakRef.get()?.onTimeTick(currentTimeMillis)
            } catch (e: Exception) {
            }
        }
    }

    interface OnTimeTickListener {
        fun onTimeTick(currentTimeMillis: Long)
    }
}
