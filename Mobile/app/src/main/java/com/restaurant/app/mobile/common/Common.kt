package com.restaurant.app.mobile.common

import android.content.Context
import android.widget.Toast
import com.restaurant.app.mobile.dto.User
import java.util.Objects

object Common {

    var user: User? = null

    var accessToken: String = ""
    var refreshToken: String = ""

    val baseUrl = "http://10.0.2.2:8080"

    fun makeToastMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun getHeaders(): MutableMap<String, String> {
        val headers = HashMap<String, String>()
        headers["Authorization"] = "Bearer ".plus(this.accessToken)
        headers["Content-Type"] = "application/json"
        return headers
    }
}