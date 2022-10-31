package com.restaurant.app.mobile.dto

import org.json.JSONObject

class City : JSONObject() {
    var id: Long = 0

    var postCode: String? = null

    var cityName: String? = null

    var longitude: Double? = null

    var latitude: Double? = null

    var restaurants: HashSet<Long> = HashSet()
}