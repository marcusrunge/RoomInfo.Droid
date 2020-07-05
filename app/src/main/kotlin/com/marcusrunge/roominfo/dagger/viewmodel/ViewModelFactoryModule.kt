package com.marcusrunge.roominfo.dagger.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.marcusrunge.roominfo.ui.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}