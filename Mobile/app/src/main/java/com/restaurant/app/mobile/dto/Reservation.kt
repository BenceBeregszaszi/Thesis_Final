package com.restaurant.app.mobile.dto

import org.json.JSONObject
import java.util.Date
import  java.io.*

class Reservation : JSONObject(), Serializable {

    var id: Long = 0

    var userId: Long = 0

    var seatNumber: Int = 0

    var cityId: Long = 0

    var restaurantId: Long = 0

    var time: Date = Date()
}