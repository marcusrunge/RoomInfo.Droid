package com.marcusrunge.roominfo.dagger.viewmodel

import androidx.lifecycle.ViewModel
import com.marcusrunge.roominfo.ui.agendaitem.AgendaItemViewModel
import com.marcusrunge.roominfo.ui.calendar.CalendarViewModel
import com.marcusrunge.roominfo.ui.home.HomeViewModel
import com.marcusrunge.roominfo.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CalendarViewModel::class)
    internal abstract fun bindCalendarViewModel(viewModel: CalendarViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AgendaItemViewModel::class)
    internal abstract fun bindAgendaItemViewModel(viewModel: AgendaItemViewModel): ViewModel
}