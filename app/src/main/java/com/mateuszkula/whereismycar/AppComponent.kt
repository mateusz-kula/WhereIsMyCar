package com.mateuszkula.whereismycar

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(ongoingTrackingActivity: OngoingTrackingActivity)
}