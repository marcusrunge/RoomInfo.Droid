package com.marcusrunge.roominfo.dagger.home

import android.content.Context
import com.marcusrunge.roominfo.dagger.ApplicationContextModule
import com.marcusrunge.roominfo.ui.home.HomeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationContextModule::class])
interface HomeViewModelComponent {
    fun inject(homeViewModel: HomeViewModel)
    fun provideContext(): Context
}