package com.mateuszkula.whereismycar

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.content.SharedPreferences
import android.support.v4.app.FragmentActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_ongoingtracking.*
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log

class OngoingTrackingActivity : FragmentActivity(), OnMapReadyCallback {

    private val PREFS_FILENAME = "abc"

    var sharedPreferences : SharedPreferences? = null
    var latitude : Float? = null
    var longitude: Float? = null

    private var locationManager : LocationManager? = null

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ongoingtracking)

        // Register for location updates
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        if (locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.d("Location", "GPS is enabled")
            locationManager!!.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000,
                    1f,
                    object: LocationListener {
                        override fun onProviderDisabled(provider: String?) {}

                        override fun onProviderEnabled(provider: String?) {}

                        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

                        override fun onLocationChanged(location: Location) {
                            Log.i("LatLon", "${location.longitude.toString()} ${location.latitude.toString()}")
                        }
                    }
            )
        }
        // -----------------------

        sharedPreferences = getSharedPreferences(PREFS_FILENAME, 0)

        longitude = sharedPreferences!!.getFloat("longitude", 0f)
        latitude = sharedPreferences!!.getFloat("latitude", 0f)

        latitudeView.setText(latitude.toString())
        longitudeView.setText(longitude.toString())

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }



    override fun onMapReady(map: GoogleMap) {
        val position = LatLng(latitude!!.toDouble(), longitude!!.toDouble())

        try {
            map.isMyLocationEnabled = true
        }
        catch (ex: SecurityException) {

        }
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL)
        map.animateCamera(CameraUpdateFactory.newLatLngBounds(
                LatLngBounds(
                        LatLng(position.latitude - 1, position.longitude - 1),
                        LatLng(position.latitude + 1, position.longitude + 1)), 50))

        map.addMarker(MarkerOptions().position(position).title("Tracking"))
    }
}
