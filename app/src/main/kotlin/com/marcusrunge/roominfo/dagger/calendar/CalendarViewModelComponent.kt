package com.marcusrunge.roominfo.dagger.calendar

import android.content.Context
import com.marcusrunge.roominfo.dagger.ApplicationContextModule
import com.marcusrunge.roominfo.dagger.data.DataModule
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.ui.calendar.CalendarViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationContextModule::class, DataModule::class])
interface CalendarViewModelComponent {
    fun inject(calendarViewModel: CalendarViewModel)
    fun provideContext(): Context
    fun provideData(): Data
}