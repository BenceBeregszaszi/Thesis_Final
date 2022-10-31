package com.restaurant.app.mobile.dto

import java.util.Date

class ReservationRepresentation {

    var id: Long? = null

    var user: String? = null

    var seatNumber : Int? = null

    var city: String? = null

    var restaurant: String? = null;

    var time: Date = Date()
}