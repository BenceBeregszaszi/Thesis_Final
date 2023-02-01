package com.restaurant.app.mobile.common

import android.content.Context
import android.widget.Toast

object Common {
    var username: String = ""

    var accessToken: String = ""
    var refreshToken: String = ""

    val baseUrl = "http://10.0.2.2:8080"

    fun makeToastMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}