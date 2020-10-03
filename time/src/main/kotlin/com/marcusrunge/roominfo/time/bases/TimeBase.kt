package com.marcusrunge.roominfo.time.bases

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.marcusrunge.roominfo.time.interfaces.Time
import com.marcusrunge.roominfo.time.receiver.TimeTickReceiver
import java.lang.ref.WeakReference

internal abstract class TimeBase(val context: Context) : Time, TimeTickReceiver.OnTimeTickListener {
    private val onTimeTickUnits: MutableList<WeakReference<(Long) -> Unit?>> = mutableListOf()
    private val timeTickReceiver: TimeTickReceiver = TimeTickReceiver()

    override fun onTimeTick(currentTimeMillis: Long) {
        invokeOnTimeTickUnits(currentTimeMillis)
    }

    fun initTimeTick() {
        val timeTickFilter = IntentFilter(Intent.ACTION_TIME_TICK)
        context.registerReceiver(timeTickReceiver, timeTickFilter)
        timeTickReceiver.addOnTimeTickListener(this)
    }

    fun finalizeTimeTick() {
        context.unregisterReceiver(timeTickReceiver)
        timeTickReceiver.removeOnTimeTickListener(this)
    }

    private fun invokeOnTimeTickUnits(currentTimeMillis: Long) {
        for (weakRef in onTimeTickUnits) {
            try {
                weakRef.get()?.invoke(currentTimeMillis)
            } catch (e: Exception) {
            }
        }
    }

    fun addOnTimeTickUnit(onTimeTickUnit: (Long) -> Unit?) {
        onTimeTickUnits.add(WeakReference(onTimeTickUnit))
    }

    fun removeOnTimeTickListener(onTimeTickUnit: (Long) -> Unit?) {
        val iterator: MutableIterator<WeakReference<(Long) -> Unit?>> =
            onTimeTickUnits.iterator()
        while (iterator.hasNext()) {
            val weakRef: WeakReference<(Long) -> Unit?> = iterator.next()
            if (weakRef.get() === onTimeTickUnit) {
                iterator.remove()
            }
        }
    }
}