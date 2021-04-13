package com.marcusrunge.roominfo.time.bases

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import com.marcusrunge.roominfo.time.interfaces.Time
import com.marcusrunge.roominfo.time.receiver.TimeTickReceiver
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*

internal abstract class TimeBase(
    val context: Context,
    val data: Data,
    val preferences: Preferences
) : Time,
    TimeTickReceiver.OnTimeTickListener {
    private val onTimeTickListener: MutableList<WeakReference<((String?) -> Unit)?>> =
        mutableListOf()
    private val onDateTickListener: MutableList<WeakReference<((String?) -> Unit)?>> =
        mutableListOf()
    private val timeTickReceiver: TimeTickReceiver = TimeTickReceiver()
    private val calendar = Calendar.getInstance()
    private val simpleTimeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val simpleDateFormat = SimpleDateFormat("dd. MMMM YYYY", Locale.getDefault())

    override fun onTimeTick(currentTimeMillis: Long) {
        invokeOnTimeTickListener(currentTimeMillis)
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

    private fun invokeOnTimeTickListener(currentTimeMillis: Long) {
        calendar.timeInMillis = currentTimeMillis
        for (weakRef in onTimeTickListener) {
            try {
                weakRef.get()?.invoke(simpleTimeFormat.format(calendar.time))
            } catch (e: Exception) {
            }
        }
        for (weakRef in onDateTickListener) {
            try {
                weakRef.get()?.invoke(simpleDateFormat.format(calendar.time))
            } catch (e: Exception) {
            }
        }
    }

    fun addOnTimeTickListener(onTimeTickListener: ((String?) -> Unit)?) {
        this.onTimeTickListener.add(WeakReference(onTimeTickListener))
        invokeOnTimeTickListener(System.currentTimeMillis())
    }

    fun addOnDateTickListener(onDateTickListener: ((String?) -> Unit)?) {
        this.onDateTickListener.add(WeakReference(onDateTickListener))
        invokeOnTimeTickListener(System.currentTimeMillis())
    }

    fun removeOnTimeTickListener(onTimeTickListener: ((String?) -> Unit)?) {
        val iterator: MutableIterator<WeakReference<((String?) -> Unit)?>> =
            this.onTimeTickListener.iterator()
        while (iterator.hasNext()) {
            val weakRef: WeakReference<((String?) -> Unit)?> = iterator.next()
            if (weakRef.get() === onTimeTickListener) {
                iterator.remove()
            }
        }
    }

    fun removeOnDateTickListener(onTimeTickListener: ((String?) -> Unit)?) {
        val iterator: MutableIterator<WeakReference<((String?) -> Unit)?>> =
            onDateTickListener.iterator()
        while (iterator.hasNext()) {
            val weakRef: WeakReference<((String?) -> Unit)?> = iterator.next()
            if (weakRef.get() === onTimeTickListener) {
                iterator.remove()
            }
        }
    }
}