package com.marcusrunge.roominfo.dagger.data

import com.marcusrunge.roominfo.data.interfaces.Data
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface DataComponent {
    fun provideData(): Data
}