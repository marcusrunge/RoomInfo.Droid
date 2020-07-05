package com.marcusrunge.roominfo.dagger

import com.marcusrunge.roominfo.ui.calendar.CalendarFragment
import com.marcusrunge.roominfo.ui.home.HomeFragment
import com.marcusrunge.roominfo.ui.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeCalendarFragment(): CalendarFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingsFragment(): SettingsFragment
}