package com.restaurant.app.mobile.dto

import org.json.JSONObject
import  java.io.*

class Restaurant : JSONObject(), Serializable {
    var id: Long? = null

    var name: String? = null

    var maxSeatsNumber: Int? = null

    var cities: HashSet<Long> = HashSet()
}