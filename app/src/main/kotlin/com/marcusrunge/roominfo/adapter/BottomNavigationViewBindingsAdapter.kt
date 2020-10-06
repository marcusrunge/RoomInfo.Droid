package com.marcusrunge.roominfo.adapter

import android.content.res.ColorStateList
import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.marcusrunge.roominfo.R
import com.marcusrunge.roominfo.RoomInfoApplication

object BottomNavigationViewBindingsAdapter {
    @BindingAdapter("setOccupancy")
    @JvmStatic
    fun bindOccupancy(view: View?, occupancy: Int?) {
        if (view is BottomNavigationView) {
            var background = 0
            var foreground = 0
            when (occupancy) {
                RoomInfoApplication.OCCUPANCY_STATE_FREE -> {
                    background =
                        view.context.resources.getColor(R.color.freeBackground, view.context.theme)
                    foreground =
                        view.context.resources.getColor(R.color.freeForeground, view.context.theme)
                }
                RoomInfoApplication.OCCUPANCY_STATE_PRESENT -> {
                    background = view.context.resources.getColor(
                        R.color.presentBackground,
                        view.context.theme
                    )
                    foreground = view.context.resources.getColor(
                        R.color.presentForeground,
                        view.context.theme
                    )
                }
                RoomInfoApplication.OCCUPANCY_STATE_ABSENT -> {
                    background = view.context.resources.getColor(
                        R.color.absentBackground,
                        view.context.theme
                    )
                    foreground = view.context.resources.getColor(
                        R.color.absentForeground,
                        view.context.theme
                    )
                }
                RoomInfoApplication.OCCUPANCY_STATE_BUSY -> {
                    background =
                        view.context.resources.getColor(R.color.busyBackground, view.context.theme)
                    foreground =
                        view.context.resources.getColor(R.color.busyForeground, view.context.theme)
                }
                RoomInfoApplication.OCCUPANCY_STATE_OCCUPIED -> {
                    background = view.context.resources.getColor(
                        R.color.occupiedBackground,
                        view.context.theme
                    )
                    foreground = view.context.resources.getColor(
                        R.color.occupiedForeground,
                        view.context.theme
                    )
                }
                RoomInfoApplication.OCCUPANCY_STATE_LOCKED -> {
                    background = view.context.resources.getColor(
                        R.color.lockedBackground,
                        view.context.theme
                    )
                    foreground = view.context.resources.getColor(
                        R.color.lockedForeground,
                        view.context.theme
                    )
                }
                RoomInfoApplication.OCCUPANCY_STATE_HOMEOFFICE -> {
                    background =
                        view.context.resources.getColor(R.color.homeBackground, view.context.theme)
                    foreground =
                        view.context.resources.getColor(R.color.homeForeground, view.context.theme)
                }
            }
            view.setBackgroundColor(background)
            view.itemIconTintList = ColorStateList.valueOf(foreground)
            view.itemTextColor = ColorStateList.valueOf(foreground)
        }
    }
}