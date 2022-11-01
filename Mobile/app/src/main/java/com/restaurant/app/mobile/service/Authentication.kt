package com.restaurant.app.mobile.service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.restaurant.app.mobile.common.CommonProperties
import com.restaurant.app.mobile.common.MapResponseToObj
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.AuthenticationRequest
import com.restaurant.app.mobile.dto.TokenPair
import com.restaurant.app.mobile.dto.User
import org.json.JSONObject

object Authentication : MapResponseToObj<TokenPair>{

    private const val authenticationUrl: String = "http://localhost:8080/authentication"

    fun logIn(body: AuthenticationRequest, callback: VolleyCallback<TokenPair>){
        val request = JsonObjectRequest(Request.Method.POST, "$authenticationUrl/authenticate", body,
            {
                    response -> val tokens = mapToObj(response)
                                callback.onSuccess(tokens)
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    fun refreshTokens(body: TokenPair, callback: VolleyCallback<TokenPair>) {
        val request = JsonObjectRequest(Request.Method.POST, "$authenticationUrl/authenticate", body,
            {
                    response -> val tokens = mapToObj(response)
                                    callback.onSuccess(tokens)
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    fun logOut () {
        CommonProperties.accessToken = null
        CommonProperties.refreshToken = null
        CommonProperties.loggedIn = false
        CommonProperties.tabIndex = 1
    }

    fun register(body: User, context: Context, callback: VolleyCallback<User>) {
        UserService.postHttpRequest(body, context, callback)
    }


    override fun mapToObj(response: JSONObject): TokenPair {
        val tokens = TokenPair()
        tokens.accessToken = response.getString("accessToken")
        tokens.refreshToken = response.getString("refreshToken")
        return tokens
    }
}