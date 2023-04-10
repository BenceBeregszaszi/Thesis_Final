package com.restaurant.app.mobile.dto

import com.restaurant.app.mobile.common.Authority
import org.json.JSONObject
import  java.io.*
import java.util.Arrays
import java.util.Collections

class User : JSONObject(), Serializable {

    var id: Long = 0

    var username: String = "non-user"

    var password: String = ""

    var email: String = ""

    var authorities: ArrayList<Authority> = ArrayList()

    var isDisabled: Boolean = false

    var reminder: String = ""
}