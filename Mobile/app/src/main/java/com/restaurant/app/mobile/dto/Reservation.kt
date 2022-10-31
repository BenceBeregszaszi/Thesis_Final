package com.restaurant.app.mobile.dto

import org.json.JSONObject
import java.util.Date
import  java.io.*

class Reservation : JSONObject(), Serializable {

    var id: Long? = null

    var userId: Long? = null

    var seatNumber: Int? = null

    var cityId: Long? = null

    var restaurantId: Long? = null

    var time: Date = Date()
}