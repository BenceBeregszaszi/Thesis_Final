package com.restaurant.app.mobile.dto

import java.time.LocalDate

class ReservationRepresentation {

    var id: Long = 0

    var user: String = ""

    var seatNumber: Int = 0

    var city: String = ""

    var restaurant: String = ""

    var time: LocalDate = LocalDate.now()
}