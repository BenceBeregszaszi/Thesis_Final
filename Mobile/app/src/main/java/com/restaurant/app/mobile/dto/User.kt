package com.restaurant.app.mobile.dto

import com.restaurant.app.mobile.common.Authority

class User {

    var id: Long? = null

    var username: String? = null

    var password: String? = null

    var email: String? = null

    var authority: Authority? = null

    var isDisabled: Boolean = false
}