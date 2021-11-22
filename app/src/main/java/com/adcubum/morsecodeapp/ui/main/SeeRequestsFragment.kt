package com.adcubum.morsecodeapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adcubum.morsecodeapp.R
import com.adcubum.morsecodeapp.core.Request
import com.adcubum.morsecodeapp.persistence.DatabaseAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class SeeRequestsFragment() : Fragment() {

    private val databaseAdapter = DatabaseAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )
            : View? {
        val rootView = inflater.inflate(R.layout.fragment_see_requests, container, false)

        databaseAdapter.updateListData(rootView)

        val refreshButton: FloatingActionButton = rootView.findViewById(R.id.refreshButton)

        refreshButton.setOnClickListener { view ->
            databaseAdapter.updateListData(rootView)
        }

        return rootView
    }



}