package com.restaurant.app.mobile.common

import org.json.JSONObject

interface MapResponseToObj<T> {

    fun mapToObj(response: JSONObject) : T
}