package com.adcubum.morsecodeapp.core

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import com.adcubum.morsecodeapp.R
import com.adcubum.morsecodeapp.persistence.RequestsDatabase

class MorseCodeService(context: Context) : Activity() {
    val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.beepsound)
    val requestsDatabase = RequestsDatabase()

    fun sendSOS() {
        mediaPlayer.start()
        requestsDatabase.saveRequest(GeoLocation(55.1111, 44.323234))
    }

}