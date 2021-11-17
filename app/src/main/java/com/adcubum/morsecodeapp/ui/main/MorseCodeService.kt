package com.adcubum.morsecodeapp.ui.main

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import com.adcubum.morsecodeapp.GeoLocation
import com.adcubum.morsecodeapp.R
import com.adcubum.morsecodeapp.Request
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import kotlin.random.Random

class MorseCodeService(val context: Context) : Activity()
//    ,LocationListener
    {
    val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.beepsound)
    private val db =
        FirebaseDatabase.getInstance("https://morse-code-app-f2cb6-default-rtdb.europe-west1.firebasedatabase.app")
//
//    private val locationPermissionCode = 2
//    lateinit var locationManager: LocationManager

    fun sendSOS() {
        mediaPlayer.start()
        saveDataToDb()
//        getLocation()
    }

    private fun saveDataToDb() {

        val request = Request(
            GeoLocation(45.588498, 55.348334),
            LocalDateTime.now(),
        )

        val requests = db.getReference("requests")
        requests.child(Random.nextInt().toString()).setValue(request)

    }
//
//    private fun getLocation() {
//        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        if ((ContextCompat.checkSelfPermission(
//                context,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED)
//        ) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                locationPermissionCode
//            )
//        }
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
//
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        if (requestCode == locationPermissionCode) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    override fun onLocationChanged(location: Location) {
//        println("Long: " + location.altitude + " Lat: " + location.longitude)
//    }


}