package com.adcubum.morsecodeapp

import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import kotlin.random.Random

class RequestsDatabase {

    private val db =
        FirebaseDatabase.getInstance("https://morse-code-app-f2cb6-default-rtdb.europe-west1.firebasedatabase.app")

    fun saveRequest(geoLocation: GeoLocation) {
        val request = Request(
            GeoLocation(
                geoLocation.long,
                geoLocation.lat
            ), //TODO("get the getGeoLocation function working from commit 199cd2c99")
            LocalDateTime.now(),
        )
        val requests = db.getReference("requests")
        requests.child(Random.nextInt().toString()).setValue(request)
    }

    fun getRequests() {
        db.getReference("requests").get().result //TODO("see how I can get a list of requests back")
    }
}