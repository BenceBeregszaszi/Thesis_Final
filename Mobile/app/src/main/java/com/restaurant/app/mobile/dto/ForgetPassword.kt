package com.restaurant.app.mobile.dto

import org.json.JSONObject
import java.io.*

class ForgetPassword : JSONObject(), Serializable {

    var email: String = ""

    var reminder: String = ""

    var newPassword: String = ""
}