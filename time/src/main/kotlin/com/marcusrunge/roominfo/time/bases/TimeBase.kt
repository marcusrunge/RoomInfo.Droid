package com.marcusrunge.roominfo.time.bases

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.marcusrunge.roominfo.time.interfaces.Time
import com.marcusrunge.roominfo.time.receiver.TimeTickReceiver
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*

internal abstract class TimeBase(val context: Context) : Time, TimeTickReceiver.OnTimeTickListener {
    private val onTimeTickUnits: MutableList<WeakReference<((String?) -> Unit)?>> = mutableListOf()
    private val onDateTickUnits: MutableList<WeakReference<((String?) -> Unit)?>> = mutableListOf()
    private val timeTickReceiver: TimeTickReceiver = TimeTickReceiver()
    private val calendar = Calendar.getInstance()
    private val simpleTimeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val simpleDateFormat = SimpleDateFormat("dd. MMMM YYYY", Locale.getDefault())

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
        calendar.timeInMillis = currentTimeMillis
        for (weakRef in onTimeTickUnits) {
            try {
                weakRef.get()?.invoke(simpleTimeFormat.format(calendar.time))
            } catch (e: Exception) {
            }
        }
        for (weakRef in onDateTickUnits) {
            try {
                weakRef.get()?.invoke(simpleDateFormat.format(calendar.time))
            } catch (e: Exception) {
            }
        }
    }

    fun addOnTimeTickUnit(onTimeTickUnit: ((String?) -> Unit)?) {
        onTimeTickUnits.add(WeakReference(onTimeTickUnit))
        invokeOnTimeTickUnits(System.currentTimeMillis())
    }

    fun addOnDateTickUnit(onDateTickUnit: ((String?) -> Unit)?) {
        onDateTickUnits.add(WeakReference(onDateTickUnit))
        invokeOnTimeTickUnits(System.currentTimeMillis())
    }

    fun removeOnTimeTickListener(onTimeTickUnit: ((String?) -> Unit)?) {
        val iterator: MutableIterator<WeakReference<((String?) -> Unit)?>> =
            onTimeTickUnits.iterator()
        while (iterator.hasNext()) {
            val weakRef: WeakReference<((String?) -> Unit)?> = iterator.next()
            if (weakRef.get() === onTimeTickUnit) {
                iterator.remove()
            }
        }
    }

    fun removeOnDateTickListener(onTimeTickUnit: ((String?) -> Unit)?) {
        val iterator: MutableIterator<WeakReference<((String?) -> Unit)?>> =
            onDateTickUnits.iterator()
        while (iterator.hasNext()) {
            val weakRef: WeakReference<((String?) -> Unit)?> = iterator.next()
            if (weakRef.get() === onTimeTickUnit) {
                iterator.remove()
            }
        }
    }
}