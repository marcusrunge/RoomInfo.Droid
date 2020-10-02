package com.marcusrunge.roominfo.dagger.home

import com.marcusrunge.roominfo.dagger.applicationresource.ApplicationResourceModule
import com.marcusrunge.roominfo.dagger.data.DataModule
import com.marcusrunge.roominfo.dagger.preferences.PreferencesModule
import com.marcusrunge.roominfo.dagger.time.TimeModule
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.preferences.interfaces.Preferences
import com.marcusrunge.roominfo.time.interfaces.Time
import com.marcusrunge.roominfo.ui.home.HomeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationResourceModule::class, PreferencesModule::class, DataModule::class, TimeModule::class])
interface HomeViewModelComponent {
    fun inject(homeViewModel: HomeViewModel)
    fun provideApplicationResource(): ApplicationResource
    fun providePreferences(): Preferences
    fun provideData(): Data
    fun provideTime(): Time
}