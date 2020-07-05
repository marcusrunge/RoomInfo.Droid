package com.marcusrunge.roominfo.dagger.main

import android.content.Context
import com.marcusrunge.roominfo.dagger.ApplicationContextModule
import com.marcusrunge.roominfo.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationContextModule::class])
interface MainViewModelComponent {
    fun inject(mainViewModel: MainViewModel)
    fun provideContext(): Context
}