package com.mateuszkula.whereismycar

import android.app.Application

class WhereIsMyCarApp : Application() {

    companion object {
        @JvmStatic lateinit var graph: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}