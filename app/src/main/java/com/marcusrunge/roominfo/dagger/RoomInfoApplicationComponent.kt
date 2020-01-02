package com.marcusrunge.roominfo.dagger

import com.marcusrunge.roominfo.RoomInfoApplication
import com.marcusrunge.roominfo.dagger.settingsservice.SettingsServiceModule
import com.marcusrunge.roominfo.dagger.viewmodel.ViewModelFactoryModule
import com.marcusrunge.roominfo.dagger.viewmodel.ViewModelModule
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
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        SettingsServiceModule::class]
)
interface RoomInfoApplicationComponent {
    fun inject(roomInfoApplication: RoomInfoApplication)
}