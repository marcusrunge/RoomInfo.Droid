package com.marcusrunge.roominfo.adapter

import android.content.res.ColorStateList
import android.util.TypedValue
import android.view.View
import android.widget.CalendarView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton
import com.marcusrunge.roominfo.R
import com.marcusrunge.roominfo.RoomInfoApplication.Companion.OCCUPANCY_STATE_ABSENT
import com.marcusrunge.roominfo.RoomInfoApplication.Companion.OCCUPANCY_STATE_BUSY
import com.marcusrunge.roominfo.RoomInfoApplication.Companion.OCCUPANCY_STATE_FREE
import com.marcusrunge.roominfo.RoomInfoApplication.Companion.OCCUPANCY_STATE_HOMEOFFICE
import com.marcusrunge.roominfo.RoomInfoApplication.Companion.OCCUPANCY_STATE_LOCKED
import com.marcusrunge.roominfo.RoomInfoApplication.Companion.OCCUPANCY_STATE_OCCUPIED
import com.marcusrunge.roominfo.RoomInfoApplication.Companion.OCCUPANCY_STATE_PRESENT

object GenericViewBindingsAdapter {
    @BindingAdapter("state", "transparent", "reverse")
    @JvmStatic
    fun bindOccupancyState(view: View?, state: Int?, transparent: Boolean?, reverse: Boolean?) {
        if (view != null && state != null) {
            var background = 0
            var buttonForeground = 0
            var transparentBackground = 0
            val typedValue = TypedValue()
            when (state) {
                OCCUPANCY_STATE_FREE -> {
                    view.context.theme.resolveAttribute(R.attr.freeForeground, typedValue, true)
                    background =
                        view.context.resources.getColor(R.color.freeBackground, view.context.theme)
                    transparentBackground = view.context.resources.getColor(
                        R.color.freeTransparentBackground,
                        view.context.theme
                    )
                    buttonForeground =
                        view.context.resources.getColor(
                            R.color.freeForegroundNotNight,
                            view.context.theme
                        )
                }
                OCCUPANCY_STATE_PRESENT -> {
                    view.context.theme.resolveAttribute(R.attr.presentForeground, typedValue, true)
                    background = view.context.resources.getColor(
                        R.color.presentBackground,
                        view.context.theme
                    )
                    transparentBackground = view.context.resources.getColor(
                        R.color.presentTransparentBackground,
                        view.context.theme
                    )
                    buttonForeground = view.context.resources.getColor(
                        R.color.presentForegroundNotNight,
                        view.context.theme
                    )
                }
                OCCUPANCY_STATE_ABSENT -> {
                    view.context.theme.resolveAttribute(R.attr.absentForeground, typedValue, true)
                    background = view.context.resources.getColor(
                        R.color.absentBackground,
                        view.context.theme
                    )
                    transparentBackground = view.context.resources.getColor(
                        R.color.absentTransparentBackground,
                        view.context.theme
                    )
                    buttonForeground = view.context.resources.getColor(
                        R.color.absentForegroundNotNight,
                        view.context.theme
                    )
                }
                OCCUPANCY_STATE_BUSY -> {
                    view.context.theme.resolveAttribute(R.attr.busyForeground, typedValue, true)
                    background =
                        view.context.resources.getColor(R.color.busyBackground, view.context.theme)
                    transparentBackground = view.context.resources.getColor(
                        R.color.busyTransparentBackground,
                        view.context.theme
                    )
                    buttonForeground =
                        view.context.resources.getColor(
                            R.color.busyForegroundNotNight,
                            view.context.theme
                        )
                }
                OCCUPANCY_STATE_OCCUPIED -> {
                    view.context.theme.resolveAttribute(R.attr.occupiedForeground, typedValue, true)
                    background = view.context.resources.getColor(
                        R.color.occupiedBackground,
                        view.context.theme
                    )
                    transparentBackground = view.context.resources.getColor(
                        R.color.occupiedTransparentBackground,
                        view.context.theme
                    )
                    buttonForeground = view.context.resources.getColor(
                        R.color.occupiedForegroundNotNight,
                        view.context.theme
                    )
                }
                OCCUPANCY_STATE_LOCKED -> {
                    view.context.theme.resolveAttribute(R.attr.lockedForeground, typedValue, true)
                    background = view.context.resources.getColor(
                        R.color.lockedBackground,
                        view.context.theme
                    )
                    transparentBackground = view.context.resources.getColor(
                        R.color.lockedTransparentBackground,
                        view.context.theme
                    )
                    buttonForeground = view.context.resources.getColor(
                        R.color.lockedForegroundNotNight,
                        view.context.theme
                    )
                }
                OCCUPANCY_STATE_HOMEOFFICE -> {
                    view.context.theme.resolveAttribute(R.attr.homeForeground, typedValue, true)
                    background =
                        view.context.resources.getColor(R.color.homeBackground, view.context.theme)
                    transparentBackground = view.context.resources.getColor(
                        R.color.homeTransparentBackground,
                        view.context.theme
                    )
                    buttonForeground =
                        view.context.resources.getColor(
                            R.color.homeForegroundNotNight,
                            view.context.theme
                        )
                }
            }

            when {
                view is AppCompatButton -> {
                    view.setTextColor(buttonForeground)
                    view.backgroundTintList = ColorStateList.valueOf(background)
                    view.foregroundTintList = ColorStateList.valueOf(buttonForeground)
                }
                view is MaterialButton -> {
                    view.backgroundTintList = ColorStateList.valueOf(background)
                    view.setTextColor(buttonForeground)
                }
                view is Spinner -> {
                    view.setBackgroundColor(background)
                    if (view.selectedView is TextView) (view.selectedView as TextView).setTextColor(
                        buttonForeground
                    )
                }
                view is TextView -> {
                    if (reverse!!) view.setTextColor(buttonForeground)
                    else
                        view.setTextColor(typedValue.data)
                }
                transparent != null && transparent -> view.setBackgroundColor(
                    transparentBackground
                )
                view is CalendarView -> {

                }
                else -> view.setBackgroundColor(background)
            }
        }
    }
}