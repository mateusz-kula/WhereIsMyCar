package com.mateuszkula.whereismycar.instantfeature

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.SharedPreferences
import kotlinx.android.synthetic.main.activity_ongoingtracking.*

/**
 * Created by mateusz.kula on 1/24/18.
 */

class OngoingTrackingActivity : AppCompatActivity() {

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
    }

}
