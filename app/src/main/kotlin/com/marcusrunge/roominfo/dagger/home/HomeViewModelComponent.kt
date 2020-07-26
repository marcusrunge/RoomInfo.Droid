package com.marcusrunge.roominfo.dagger.home

import com.marcusrunge.roominfo.dagger.applicationresource.ApplicationResourceModule
import com.marcusrunge.roominfo.dagger.data.DataModule
import com.marcusrunge.roominfo.dagger.settingsservice.SettingsServiceModule
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.interfaces.SettingsService
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.ui.home.HomeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationResourceModule::class, SettingsServiceModule::class, DataModule::class])
interface HomeViewModelComponent {
    fun inject(homeViewModel: HomeViewModel)
    fun provideApplicationResource(): ApplicationResource
    fun provideSettingsService(): SettingsService
    fun provideData(): Data
}