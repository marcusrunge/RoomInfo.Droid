package com.marcusrunge.roominfo.dagger

import com.marcusrunge.roominfo.RoomInfoApplication
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationContextModule::class,
        ActivityModule::class,
        FragmentModule::class]
)
interface RoomInfoApplicationComponent {
    fun inject(roomInfoApplication: RoomInfoApplication)
}