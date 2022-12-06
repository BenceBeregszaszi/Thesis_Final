package com.restaurant.app.mobile.common

import com.restaurant.app.mobile.dto.TokenPair

object CommonProperties {

    var accessToken: String? = null

    var refreshToken: String? = null

    var tabIndex: Int = -1

    var loggedIn: Boolean = false

    fun setTokens(tokens: TokenPair) {

    }
}