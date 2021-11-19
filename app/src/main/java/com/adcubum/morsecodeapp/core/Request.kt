package com.adcubum.morsecodeapp.core

import java.time.LocalDateTime

data class Request(val message: String, val geoLocation: GeoLocation? = null, val timestamp: LocalDateTime)

data class GeoLocation(val long: Double, val lat: Double)
