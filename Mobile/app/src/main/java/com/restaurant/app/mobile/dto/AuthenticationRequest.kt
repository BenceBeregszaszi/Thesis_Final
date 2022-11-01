package com.restaurant.app.mobile.dto

import org.json.JSONObject
import java.io.*

class AuthenticationRequest : JSONObject(), Serializable {

    var username: String = ""

    var password: String = ""
}