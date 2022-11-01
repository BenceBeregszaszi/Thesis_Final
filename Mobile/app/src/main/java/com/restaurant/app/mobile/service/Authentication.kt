package com.restaurant.app.mobile.service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.restaurant.app.mobile.common.CommonProperties
import com.restaurant.app.mobile.common.MapResponseToObj
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.AuthenticationRequest
import com.restaurant.app.mobile.dto.TokenPair
import com.restaurant.app.mobile.dto.User
import org.json.JSONObject

object Authentication : MapResponseToObj<TokenPair>{

    private const val authenticationUrl: String = "http://10.0.2.2:8080/authentication"

    fun logIn(body: AuthenticationRequest, context: Context, callback: VolleyCallback<TokenPair>){
        val newObj = JSONObject()
        newObj.put("username", body.username)
        newObj.put("password", body.password)
        val request = JsonObjectRequest(Request.Method.POST, "$authenticationUrl/authenticate", newObj,
            {
                    response -> val tokens = mapToObj(response)
                                callback.onSuccess(tokens)
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun refreshTokens(body: TokenPair, context: Context, callback: VolleyCallback<TokenPair>) {
        val newObj = JSONObject()
        newObj.put("refreshToken", body.refreshToken)
        newObj.put("accessToken", body.accessToken)
        val request = JsonObjectRequest(Request.Method.POST, "$authenticationUrl/authenticate", newObj,
            {
                    response -> val tokens = mapToObj(response)
                                    callback.onSuccess(tokens)
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
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