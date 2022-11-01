package com.restaurant.app.mobile.dto

import org.json.JSONObject
import  java.io.*

class Restaurant : JSONObject(), Serializable {
    var id: Long = 0

    var name: String = ""

    var maxSeatsNumber: Int = 0

    var address: String = ""

    var cities: HashSet<Long> = HashSet()
}