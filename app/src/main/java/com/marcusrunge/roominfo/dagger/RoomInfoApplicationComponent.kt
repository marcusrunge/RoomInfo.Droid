package com.marcusrunge.roominfo.dagger

import com.marcusrunge.roominfo.RoomInfoApplication
import com.marcusrunge.roominfo.dagger.settingsservice.SettingsServiceModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationContextModule::class,
        ActivityModule::class,
        FragmentModule::class,
        SettingsServiceModule::class]
)
interface RoomInfoApplicationComponent {
    fun inject(roomInfoApplication: RoomInfoApplication)
}