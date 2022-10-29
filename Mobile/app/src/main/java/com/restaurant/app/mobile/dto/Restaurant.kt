package com.restaurant.app.mobile.dto

import org.json.JSONObject

class Restaurant : JSONObject() {
    var id: Long? = null

    var name: String? = null

    var maxSeatsNumber: Int? = null

    var cities: Set<Long>? = null
}