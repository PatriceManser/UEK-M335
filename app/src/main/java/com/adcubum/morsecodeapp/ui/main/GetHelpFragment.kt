package com.adcubum.morsecodeapp.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.adcubum.morsecodeapp.service.MorseCodeService
import com.adcubum.morsecodeapp.R
import com.google.android.material.snackbar.Snackbar

class GetHelpFragment(context: Context) : Fragment() {

    val morseCodeService = MorseCodeService(context)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )
    : View? {
        val view = inflater.inflate(R.layout.fragment_get_help, container, false)
        val sosButton: Button = view.findViewById(R.id.sosButton)
        val helpMessageView: EditText  = view.findViewById(R.id.helpMessage)

        sosButton.setOnClickListener { view ->
            morseCodeService.sendSOS(helpMessageView.text.toString())
            Snackbar.make(view, "Rescue is on the way", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        return view
    }

}