package com.marcusrunge.roominfo.dagger.navigation

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.marcusrunge.roominfo.R.id
import com.marcusrunge.roominfo.dagger.applicationresource.ApplicationResourceModule
import com.marcusrunge.roominfo.models.ApplicationResource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationResourceModule::class])
class NavigationModule {
    @Provides
    @Singleton
    fun provideNavController(applicationResource: ApplicationResource): NavController {
        return applicationResource.mainActivity!!.findNavController(id.nav_host_fragment)
    }
}