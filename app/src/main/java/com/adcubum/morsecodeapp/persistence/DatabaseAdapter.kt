package com.adcubum.morsecodeapp.persistence

import android.R
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.adcubum.morsecodeapp.core.GeoLocation
import com.adcubum.morsecodeapp.core.Request
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random


class DatabaseAdapter {

    val db =
        FirebaseDatabase.getInstance("https://morse-code-app-f2cb6-default-rtdb.europe-west1.firebasedatabase.app")
    val ref = db.getReference("requests")

    fun saveRequest(message: String, geoLocation: GeoLocation) {
        val request = Request(
            message,
            GeoLocation(
                geoLocation.long,
                geoLocation.lat
            ), //TODO("get the getGeoLocation function working from commit 199cd2c99")
            LocalDateTime.now(),
        )
        val requests = db.getReference("requests")
        requests.child(Random.nextInt().toString()).setValue(request)
        deleteOldItems()
    }

    fun updateListData(rootView: View) {
        val requestItems = emptyList<Request>().toMutableList()
        ref.addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.children.forEach {
                        val timestamp = parseLocalDateTime(it.child("timestamp"))
                        val geoLocation = GeoLocation(
                            (it.child("geoLocation").child("lat").value as Double),
                            (it.child("geoLocation").child("long").value as Double)
                        )
                        val message = it.child("message").value.toString()
                        requestItems.add(Request(message, geoLocation, timestamp))
                    }
                    val requestsListView: ListView =
                        rootView.findViewById(com.adcubum.morsecodeapp.R.id.requestsListView)

                    val arrayAdapter =
                        ArrayAdapter(
                            rootView.context,
                            R.layout.simple_list_item_1,
                            requestItems.sortedByDescending { it.timestamp }.map {
                                """
                                    |${it.message}
                                    |${it.timestamp.format(DateTimeFormatter.ofPattern("dd/MM/yyyy\thh:mm:ss"))}
                                    |GeoLocation= Long: ${it.geoLocation!!.long} Lat: ${it.geoLocation!!.lat}""".trimMargin()
                            }
                        )

                    requestsListView.adapter = arrayAdapter
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
    }

    private fun deleteOldItems() {
        var now = LocalDateTime.now()
        var cutoff = now.minusMonths(1)
        ref.addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.children.forEach {
                        val timestamp = it.child("timestamp")
                        val dateTime = parseLocalDateTime(timestamp)
                        if (dateTime < cutoff)
                            ref.child(it.key!!).removeValue()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    //handle databaseError
                }
            })
    }

    fun parseLocalDateTime(timestamp: DataSnapshot): LocalDateTime {
        return LocalDateTime.of(
            (timestamp.child("year").value as Long).toInt(),
            (timestamp.child("monthValue").value as Long).toInt(),
            (timestamp.child("dayOfMonth").value as Long).toInt(),
            (timestamp.child("hour").value as Long).toInt(),
            (timestamp.child("minute").value as Long).toInt(),
            (timestamp.child("second").value as Long).toInt(),
            (timestamp.child("nano").value as Long).toInt()
        )
    }
}