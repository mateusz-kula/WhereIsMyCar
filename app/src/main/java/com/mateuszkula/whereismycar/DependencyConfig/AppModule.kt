package com.mateuszkula.whereismycar.DependencyConfig

import android.content.Context
import android.content.SharedPreferences
import android.location.LocationManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: WhereIsMyCarApp) {

    @Provides
    @Singleton
    fun provideLocationManager(): LocationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @Provides
    @Singleton
    fun provideSharedPreferences() : SharedPreferences =
            application.getSharedPreferences("COMMON", 0)

}