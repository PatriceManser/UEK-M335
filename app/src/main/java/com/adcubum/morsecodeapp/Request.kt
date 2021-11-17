package com.adcubum.morsecodeapp

import java.time.LocalDateTime

data class Request(val geoLocation: GeoLocation? = null, val timestamp: LocalDateTime)

data class GeoLocation(val long: Double, val lat: Double)
