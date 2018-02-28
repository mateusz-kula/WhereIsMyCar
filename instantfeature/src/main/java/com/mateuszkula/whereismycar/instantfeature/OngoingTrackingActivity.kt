package com.mateuszkula.whereismycar.instantfeature

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.SharedPreferences
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_ongoingtracking.*
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng

class OngoingTrackingActivity : AppCompatActivity(), OnMapReadyCallback {

    private val PREFS_FILENAME = "abc"

    var sharedPreferences : SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ongoingtracking)

        sharedPreferences = getSharedPreferences(PREFS_FILENAME, 0)

        var longitude = sharedPreferences!!.getFloat("longitude", 0f)
        var latitude = sharedPreferences!!.getFloat("latitude", 0f)

        latitudeView.setText(latitude.toString())
        longitudeView.setText(longitude.toString())

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(MarkerOptions().position(sydney).title("Sydney"))
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
