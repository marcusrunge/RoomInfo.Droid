package com.marcusrunge.roominfo.dagger

import com.marcusrunge.roominfo.RoomInfoApplication
import com.marcusrunge.roominfo.dagger.applicationresource.ApplicationResourceModule
import com.marcusrunge.roominfo.dagger.data.DataModule
import com.marcusrunge.roominfo.dagger.navigation.NavigationModule
import com.marcusrunge.roominfo.dagger.occupancy.OccupancyModule
import com.marcusrunge.roominfo.dagger.preferences.PreferencesModule
import com.marcusrunge.roominfo.dagger.sockets.SocketsModule
import com.marcusrunge.roominfo.dagger.time.TimeModule
import com.marcusrunge.roominfo.dagger.viewmodel.ViewModelFactoryModule
import com.marcusrunge.roominfo.dagger.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        DataModule::class,
        SocketsModule::class,
        ApplicationResourceModule::class,
        NavigationModule::class,
        PreferencesModule::class,
        TimeModule::class,
        OccupancyModule::class]
)
interface RoomInfoApplicationComponent {
    fun inject(roomInfoApplication: RoomInfoApplication)
}