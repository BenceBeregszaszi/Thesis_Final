package com.restaurant.app.mobile.dto

import com.restaurant.app.mobile.interfaces.SpinnerProperty
import org.json.JSONObject
import java.io.*

class City : JSONObject(), Serializable, SpinnerProperty {
    var id: Long = 0

    var postCode: String = ""

    var cityName: String = ""

    var restaurants: HashSet<Long> = HashSet()

    override fun getItemId(): Long {
        return id
    }

    override fun getItem(): String {
        return cityName
    }
}