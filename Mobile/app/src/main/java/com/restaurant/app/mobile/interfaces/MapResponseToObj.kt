package com.restaurant.app.mobile.interfaces

import org.json.JSONObject

interface MapResponseToObj<T> {

    fun mapToObj(response: JSONObject) : T
}