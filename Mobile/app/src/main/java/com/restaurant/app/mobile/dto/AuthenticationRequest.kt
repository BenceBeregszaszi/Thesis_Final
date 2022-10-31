package com.restaurant.app.mobile.dto

import org.json.JSONObject

class AuthenticationRequest() : JSONObject() {

    constructor(username: String, password: String) : this() {
        this.username = username
        this.password = password
    }

    var username: String? = null

    var password: String? = null
}