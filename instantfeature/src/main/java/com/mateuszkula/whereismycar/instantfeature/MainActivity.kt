package com.mateuszkula.whereismycar.instantfeature

import android.app.PendingIntent
import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    private var locationManager : LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?

        var permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
        var initialRequest = 1337
        requestPermissions(permissions, initialRequest)

        startButton.setOnClickListener {
            try {
                val intent = intentFor<MainActivity>()
                var pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT)
                locationManager?.requestSingleUpdate(LocationManager.GPS_PROVIDER, pendingIntent)
                var location = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                var latitide = location?.latitude
                var longitude = location?.longitude

            } catch(ex: SecurityException) {
                // Location is not available ToDo: Handle it
            }

            startActivity<OngoingTrackingActivity>()
        }
    }
}
