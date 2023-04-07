package com.restaurant.app.mobile.dto

import com.restaurant.app.mobile.interfaces.SpinnerProperty
import org.json.JSONObject
import  java.io.*

class Restaurant : JSONObject(), Serializable, SpinnerProperty {
    var id: Long = 0

    var name: String = ""

    var maxSeatsNumber: Int = 0

    var address: String = ""

    var cities: HashSet<Long> = HashSet()

    override fun getItemId(): Long {
        return id
    }

    override fun getItem(): String {
        return name
    }
}