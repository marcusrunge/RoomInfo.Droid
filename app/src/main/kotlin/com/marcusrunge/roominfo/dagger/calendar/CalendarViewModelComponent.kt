package com.marcusrunge.roominfo.dagger.calendar

import com.marcusrunge.roominfo.dagger.applicationresource.ApplicationResourceModule
import com.marcusrunge.roominfo.dagger.data.DataModule
import com.marcusrunge.roominfo.dagger.preferences.PreferencesModule
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import com.marcusrunge.roominfo.ui.calendar.CalendarViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationResourceModule::class, DataModule::class, PreferencesModule::class])
interface CalendarViewModelComponent {
    fun inject(calendarViewModel: CalendarViewModel)
    fun provideApplicationResource(): ApplicationResource
    fun provideData(): Data
    fun providePreferences():Preferences
}