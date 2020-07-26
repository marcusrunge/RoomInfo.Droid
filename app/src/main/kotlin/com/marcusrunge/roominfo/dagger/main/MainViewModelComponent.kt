package com.marcusrunge.roominfo.dagger.main

import com.marcusrunge.roominfo.dagger.applicationresource.ApplicationResourceModule
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationResourceModule::class])
interface MainViewModelComponent {
    fun inject(mainViewModel: MainViewModel)
    fun provideApplicationResource(): ApplicationResource
}