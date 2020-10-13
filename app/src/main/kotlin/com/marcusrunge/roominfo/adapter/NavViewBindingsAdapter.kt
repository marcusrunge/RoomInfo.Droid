package com.marcusrunge.roominfo.adapter

import android.app.Activity
import android.content.res.ColorStateList
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.marcusrunge.roominfo.R
import com.marcusrunge.roominfo.RoomInfoApplication
import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import com.marcusrunge.roominfo.ui.main.MainViewModel

object NavViewBindingsAdapter {
    @BindingAdapter("bindNavController", "bindPreferences")
    @JvmStatic
    fun bindNavController(view: View, mainViewModel: MainViewModel, preferences: Preferences) {
        val navController =
            (view.context as Activity).findNavController(R.id.nav_host_fragment)
        (view as BottomNavigationView).setupWithNavController(navController)
        navController.addOnDestinationChangedListener(mainViewModel)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            var background = 0
            var foreground = 0
            var unselected = 0
            val navView = view.findViewById<BottomNavigationView>(R.id.nav_view)
            if (destination.id != R.id.navigation_home) {
                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
                    background =
                        ContextCompat.getColor(view.context, android.R.color.background_light)
                    foreground =
                        ContextCompat.getColor(view.context, android.R.color.background_dark)
                    unselected = ContextCompat.getColor(view.context, android.R.color.darker_gray)
                } else {
                    background =
                        ContextCompat.getColor(view.context, android.R.color.background_dark)
                    foreground =
                        ContextCompat.getColor(view.context, android.R.color.background_light)
                    unselected = ContextCompat.getColor(view.context, android.R.color.darker_gray)
                }
            } else {
                when (preferences.occupancy) {
                    RoomInfoApplication.OCCUPANCY_STATE_FREE -> {
                        background =
                            view.context.resources.getColor(
                                R.color.freeBackground,
                                view.context.theme
                            )
                        foreground =
                            view.context.resources.getColor(
                                R.color.freeForeground,
                                view.context.theme
                            )
                        unselected = view.context.resources.getColor(
                            R.color.freeUnSelected,
                            view.context.theme
                        )
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
                        unselected = view.context.resources.getColor(
                            R.color.presentUnSelected,
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
                        unselected = view.context.resources.getColor(
                            R.color.absentUnSelected,
                            view.context.theme
                        )
                    }
                    RoomInfoApplication.OCCUPANCY_STATE_BUSY -> {
                        background =
                            view.context.resources.getColor(
                                R.color.busyBackground,
                                view.context.theme
                            )
                        foreground =
                            view.context.resources.getColor(
                                R.color.busyForeground,
                                view.context.theme
                            )
                        unselected = view.context.resources.getColor(
                            R.color.busyUnSelected,
                            view.context.theme
                        )
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
                        unselected = view.context.resources.getColor(
                            R.color.occupiedUnSelected,
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
                        unselected = view.context.resources.getColor(
                            R.color.lockedUnSelected,
                            view.context.theme
                        )
                    }
                    RoomInfoApplication.OCCUPANCY_STATE_HOMEOFFICE -> {
                        background =
                            view.context.resources.getColor(
                                R.color.homeBackground,
                                view.context.theme
                            )
                        foreground =
                            view.context.resources.getColor(
                                R.color.homeForeground,
                                view.context.theme
                            )
                        unselected = view.context.resources.getColor(
                            R.color.homeUnSelected,
                            view.context.theme
                        )
                    }
                }
            }
            val colorStateList = ColorStateList(
                arrayOf(
                    intArrayOf(android.R.attr.state_pressed),
                    intArrayOf(android.R.attr.state_selected),
                    intArrayOf(-android.R.attr.state_selected)
                ), intArrayOf(
                    foreground,
                    foreground,
                    unselected
                )
            )
            navView.setBackgroundColor(background)
            navView.itemIconTintList = colorStateList
            navView.itemTextColor = colorStateList
        }
    }
}