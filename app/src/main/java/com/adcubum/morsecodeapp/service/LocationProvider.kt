package com.adcubum.morsecodeapp.service

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.adcubum.morsecodeapp.core.GeoLocation
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.runBlocking


class LocationProvider(val context: Context) : AppCompatActivity(),
    GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, ActivityCompat.OnRequestPermissionsResultCallback {

    private var latitude: Double = 0.001
    private var longitude: Double = 0.001

    val mGoogleApiClient = GoogleApiClient.Builder(context)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .addApi(LocationServices.API).build()


    fun getLocation(): GeoLocation {
        mGoogleApiClient.connect()

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.e("PermissionError", "The Permission was not given for the location.")
        } else {

            runBlocking {
                val location =LocationServices.getFusedLocationProviderClient(context).lastLocation
                location.addOnCompleteListener { task ->
                    val currentLocation = task.result
                    latitude = currentLocation.latitude
                    longitude = currentLocation.longitude
                }

            }

        }

        return GeoLocation(longitude, latitude)
    }


    override fun onConnectionFailed(result: ConnectionResult) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.errorCode)
    }

    override fun onConnected(arg0: Bundle?) {
        // Once connected with google api, get the location
    }

    override fun onConnectionSuspended(arg0: Int) {
        mGoogleApiClient.connect()
    }


}