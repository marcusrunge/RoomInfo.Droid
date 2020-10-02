package com.marcusrunge.roominfo.dagger.time

import com.marcusrunge.roominfo.time.interfaces.Time
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TimeModule::class])
interface TimeComponent {
    fun provideTime(): Time
}