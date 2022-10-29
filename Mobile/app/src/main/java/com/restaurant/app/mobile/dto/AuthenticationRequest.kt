package com.restaurant.app.mobile.dto

import org.json.JSONObject

class AuthenticationRequest : JSONObject() {

    var username: String? = null

    var password: String? = null
}