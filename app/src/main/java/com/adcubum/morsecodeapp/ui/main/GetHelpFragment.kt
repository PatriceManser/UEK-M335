package com.adcubum.morsecodeapp.ui.main

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.adcubum.morsecodeapp.R
import com.google.android.material.snackbar.Snackbar

class GetHelpFragment(context: Context) : Fragment() {

    val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.beepsound)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_get_help, container, false)

        val sosButton: Button = view.findViewById(R.id.sosButton)

        sosButton.setOnClickListener { view ->
            MorseCodeService(mediaPlayer).sendSOS()
            Snackbar.make(view, "Rescue is on its way", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        return view
    }


}