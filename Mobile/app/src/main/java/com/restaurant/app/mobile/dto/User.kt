package com.restaurant.app.mobile.dto

import com.restaurant.app.mobile.common.Authority
import org.json.JSONObject
import  java.io.*

class User : JSONObject(), Serializable {

    var id: Long = 0

    var username: String = ""

    var password: String = ""

    var email: String = ""

    var authority: Authority = Authority.NON_USER

    var isDisabled: Boolean = false

    var reminder: String = ""
}