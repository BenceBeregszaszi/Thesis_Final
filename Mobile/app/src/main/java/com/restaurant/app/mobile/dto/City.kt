package com.restaurant.app.mobile.dto

import org.json.JSONObject
import java.io.*

class City : JSONObject(), Serializable {
    var id: Long = 0

    var postCode: String = ""

    var cityName: String = ""

    var restaurants: HashSet<Long> = HashSet()
}