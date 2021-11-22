package com.adcubum.morsecodeapp.service

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import com.adcubum.morsecodeapp.R
import com.adcubum.morsecodeapp.core.GeoLocation
import com.adcubum.morsecodeapp.persistence.DatabaseAdapter

class MorseCodeService(context: Context) : Activity() {
    val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.beepsound)
    val requestsDatabase = DatabaseAdapter()
    val locationProvider = LocationProvider(context).also {
        it.getLocation()
    }

    fun sendSOS(message: String) {
        mediaPlayer.start()
        requestsDatabase.saveRequest(message, locationProvider.getLocation())
    }
}