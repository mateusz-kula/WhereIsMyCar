package com.mateuszkula.whereismycar.Activities

import android.app.PendingIntent
import android.content.SharedPreferences
import android.location.LocationManager
import android.os.Bundle
import android.app.*
import com.mateuszkula.whereismycar.R
import com.mateuszkula.whereismycar.DependencyConfig.WhereIsMyCarApp
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import javax.inject.Inject

class MainActivity : Activity() {

    @Inject
    lateinit var locationManager: LocationManager

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WhereIsMyCarApp.graph.inject(this)

        var permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
        var initialRequest = 1337
        requestPermissions(permissions, initialRequest)

        startButton.setOnClickListener {
            try {
                val intent = intentFor<MainActivity>()
                var pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT)
                locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, pendingIntent)

                var location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                var latitude = location.latitude
                var longitude = location.longitude

                var editor = sharedPreferences.edit()
                editor.putFloat("longitude", longitude.toFloat())
                editor.putFloat("latitude", latitude.toFloat())
                editor.apply()

            } catch(ex: SecurityException) {
                // Location is not available
            }

            startActivity<OngoingTrackingActivity>()
        }
    }
}
