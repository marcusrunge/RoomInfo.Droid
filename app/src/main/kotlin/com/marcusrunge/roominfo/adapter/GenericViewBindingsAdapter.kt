package com.marcusrunge.roominfo.adapter

import android.content.res.ColorStateList
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.view.View
import android.widget.CalendarView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.marcusrunge.roominfo.R
import com.marcusrunge.roominfo.RoomInfoApplication.Companion.OCCUPANCY_STATE_ABSENT
import com.marcusrunge.roominfo.RoomInfoApplication.Companion.OCCUPANCY_STATE_BUSY
import com.marcusrunge.roominfo.RoomInfoApplication.Companion.OCCUPANCY_STATE_FREE
import com.marcusrunge.roominfo.RoomInfoApplication.Companion.OCCUPANCY_STATE_HOMEOFFICE
import com.marcusrunge.roominfo.RoomInfoApplication.Companion.OCCUPANCY_STATE_LOCKED
import com.marcusrunge.roominfo.RoomInfoApplication.Companion.OCCUPANCY_STATE_OCCUPIED
import com.marcusrunge.roominfo.RoomInfoApplication.Companion.OCCUPANCY_STATE_PRESENT

object GenericViewBindingsAdapter {
    @BindingAdapter("state", "transparent")
    @JvmStatic
    fun bindOccupancyState(view: View?, state: Int?, transparent: Boolean?) {
        if (view != null && state != null) {
            var background = 0
            var transparentBackground = 0
            var foreground = 0
            when (state) {
                OCCUPANCY_STATE_FREE -> {
                    background =
                        view.context.resources.getColor(R.color.freeBackground, view.context.theme)
                    transparentBackground = view.context.resources.getColor(
                        R.color.freeTransparentBackground,
                        view.context.theme
                    )
                    foreground =
                        view.context.resources.getColor(R.color.freeForeground, view.context.theme)
                }
                OCCUPANCY_STATE_PRESENT -> {
                    background = view.context.resources.getColor(
                        R.color.presentBackground,
                        view.context.theme
                    )
                    transparentBackground = view.context.resources.getColor(
                        R.color.presentTransparentBackground,
                        view.context.theme
                    )
                    foreground = view.context.resources.getColor(
                        R.color.presentForeground,
                        view.context.theme
                    )
                }
                OCCUPANCY_STATE_ABSENT -> {
                    background = view.context.resources.getColor(
                        R.color.absentBackground,
                        view.context.theme
                    )
                    transparentBackground = view.context.resources.getColor(
                        R.color.absentTransparentBackground,
                        view.context.theme
                    )
                    foreground = view.context.resources.getColor(
                        R.color.absentForeground,
                        view.context.theme
                    )
                }
                OCCUPANCY_STATE_BUSY -> {
                    background =
                        view.context.resources.getColor(R.color.busyBackground, view.context.theme)
                    transparentBackground = view.context.resources.getColor(
                        R.color.busyTransparentBackground,
                        view.context.theme
                    )
                    foreground =
                        view.context.resources.getColor(R.color.busyForeground, view.context.theme)
                }
                OCCUPANCY_STATE_OCCUPIED -> {
                    background = view.context.resources.getColor(
                        R.color.occupiedBackground,
                        view.context.theme
                    )
                    transparentBackground = view.context.resources.getColor(
                        R.color.occupiedTransparentBackground,
                        view.context.theme
                    )
                    foreground = view.context.resources.getColor(
                        R.color.occupiedForeground,
                        view.context.theme
                    )
                }
                OCCUPANCY_STATE_LOCKED -> {
                    background = view.context.resources.getColor(
                        R.color.lockedBackground,
                        view.context.theme
                    )
                    transparentBackground = view.context.resources.getColor(
                        R.color.lockedTransparentBackground,
                        view.context.theme
                    )
                    foreground = view.context.resources.getColor(
                        R.color.lockedForeground,
                        view.context.theme
                    )
                }
                OCCUPANCY_STATE_HOMEOFFICE -> {
                    background =
                        view.context.resources.getColor(R.color.homeBackground, view.context.theme)
                    transparentBackground = view.context.resources.getColor(
                        R.color.homeTransparentBackground,
                        view.context.theme
                    )
                    foreground =
                        view.context.resources.getColor(R.color.homeForeground, view.context.theme)
                }
            }

            when {
                view is AppCompatButton -> {
                    view.setTextColor(foreground)
                    view.backgroundTintList = ColorStateList.valueOf(background)
                    view.foregroundTintList= ColorStateList.valueOf(foreground)
                }
                view is FloatingActionButton->{
                    view.backgroundTintList = ColorStateList.valueOf(background)
                    view.drawable.setTint(foreground)
                }
                view is Spinner -> {
                    view.setBackgroundColor(background)
                    if (view.selectedView is TextView) (view.selectedView as TextView).setTextColor(
                        foreground
                    )
                }
                view is TextView -> view.setTextColor(foreground)
                transparent != null && transparent -> view.setBackgroundColor(
                    transparentBackground
                )
                view is CalendarView->{

                }
                else -> view.setBackgroundColor(background)
            }
        }
    }
}