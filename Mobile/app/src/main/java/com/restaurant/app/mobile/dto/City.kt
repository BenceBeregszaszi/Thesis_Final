package com.restaurant.app.mobile.dto

class City {
    var id: Long = 0

    var postCode: String? = null

    var cityName: String? = null

    var longitude: Double? = null

    var latitude: Double? = null

    var restaurants: Set<Long>? = null;
}