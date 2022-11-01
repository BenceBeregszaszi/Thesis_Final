package com.restaurant.app.mobile.dto

import org.json.JSONObject

class TokenPair : JSONObject(){

    var accessToken: String = ""

    var refreshToken: String = ""
}