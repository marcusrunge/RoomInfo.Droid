package com.marcusrunge.roominfo.time.implementations

import com.marcusrunge.roominfo.time.bases.TimeBase
import com.marcusrunge.roominfo.time.interfaces.Timer
import java.lang.ref.WeakReference
import java.time.OffsetDateTime
import kotlin.concurrent.schedule

internal class TimerImpl(private val timeBase: TimeBase) : Timer {
    private val startListener: MutableList<WeakReference<(() -> Unit)?>> =
        mutableListOf()
    private val endListener: MutableList<WeakReference<(() -> Unit)?>> =
        mutableListOf()
    private var _isStartSet = false
    private var _isEndSet = false
    private var startTimer: java.util.Timer? = null
    private var endTimer: java.util.Timer? = null

    override val isStartSet: Boolean
        get() = _isStartSet

    override val isEndSet: Boolean
        get() = _isEndSet

    override var start: (() -> Unit)? = null
        set(value) = when (value) {
            null -> removeStartListener(value)
            else -> addStartListener(value)
        }

    override var end: (() -> Unit)? = null
        set(value) = when (value) {
            null -> removeEndListener(value)
            else -> addEndListener(value)
        }

    override fun setStart(start: Long) {
        val now = OffsetDateTime.now().toEpochSecond()
        val delay = start - now
        if (delay > -1) {
            abort()
            startTimer = java.util.Timer()
            startTimer!!.schedule(delay * 1000) {
                invokeStart()
                startTimer = null
            }
        }
    }

    override fun setEnd(end: Long) {
        val now = OffsetDateTime.now().toEpochSecond()
        val delay = end - now
        if (delay > -1) {
            abort()
            endTimer = java.util.Timer()
            endTimer!!.schedule(delay * 1000) {
                invokeEnd()
                endTimer = null
            }
        }
    }

    override fun abort() {
        if (startTimer != null) {
            startTimer!!.cancel()
            startTimer!!.purge()
        }
        if (endTimer != null) {
            endTimer!!.cancel()
            endTimer!!.purge()
        }
    }

    private fun addStartListener(startListener: (() -> Unit)?) {
        this.startListener.add(WeakReference(startListener))
    }

    private fun addEndListener(endListener: (() -> Unit)?) {
        this.endListener.add(WeakReference(endListener))
    }

    private fun removeStartListener(startListener: (() -> Unit)?) {
        val iterator: MutableIterator<WeakReference<(() -> Unit)?>> =
            this.startListener.iterator()
        while (iterator.hasNext()) {
            val weakRef: WeakReference<(() -> Unit)?> = iterator.next()
            if (weakRef.get() === startListener) {
                iterator.remove()
            }
        }
    }

    private fun removeEndListener(endListener: (() -> Unit)?) {
        val iterator: MutableIterator<WeakReference<(() -> Unit)?>> =
            this.endListener.iterator()
        while (iterator.hasNext()) {
            val weakRef: WeakReference<(() -> Unit)?> = iterator.next()
            if (weakRef.get() === endListener) {
                iterator.remove()
            }
        }
    }

    private fun invokeStart() {
        for (weakRef in startListener) {
            try {
                weakRef.get()?.invoke()
            } catch (e: Exception) {
            }
        }
    }

    private fun invokeEnd() {
        for (weakRef in endListener) {
            try {
                weakRef.get()?.invoke()
            } catch (e: Exception) {
            }
        }
    }
}