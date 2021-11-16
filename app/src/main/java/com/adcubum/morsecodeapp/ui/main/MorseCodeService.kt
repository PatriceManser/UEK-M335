package com.adcubum.morsecodeapp.ui.main

import android.media.MediaPlayer

class MorseCodeService(val mediaPlayer: MediaPlayer) {
    fun sendSOS() {
        mediaPlayer.start()
    }

    private fun getGeoLocation() {

    }
}