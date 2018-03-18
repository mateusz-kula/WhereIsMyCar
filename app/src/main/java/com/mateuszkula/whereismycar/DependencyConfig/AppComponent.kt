package com.mateuszkula.whereismycar.DependencyConfig

import com.mateuszkula.whereismycar.Activities.MainActivity
import com.mateuszkula.whereismycar.Activities.OngoingTrackingActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(ongoingTrackingActivity: OngoingTrackingActivity)
}