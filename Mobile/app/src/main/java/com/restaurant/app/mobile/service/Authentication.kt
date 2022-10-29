package com.restaurant.app.mobile.service

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.restaurant.app.mobile.common.MapResponseToObj
import com.restaurant.app.mobile.dto.AuthenticationRequest
import com.restaurant.app.mobile.dto.TokenPair
import com.restaurant.app.mobile.dto.User
import org.json.JSONObject

object Authentication : MapResponseToObj<TokenPair>{

    private val authenticationUrl: String = "http://localhost:8080/authentication"

    fun logIn(body: AuthenticationRequest) : TokenPair{
        var tokens = TokenPair()
        JsonObjectRequest(Request.Method.POST, "$authenticationUrl/authenticate", body,
            {
                    response -> tokens = mapToObj(response)
            },
            {
                    error -> Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
        return tokens
    }

    fun refreshTokens(body: TokenPair) : TokenPair {
        var tokens = TokenPair()
        JsonObjectRequest(Request.Method.POST, "$authenticationUrl/authenticate", body,
            {
                    response -> tokens = mapToObj(response)
            },
            {
                    error -> Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
        return tokens
    }

    fun logOut () {
        //TODO: IMPLEMENT
    }

    fun register(user: User) : User {
        var user: User = UserService.postHttpRequest(user)

        return user
    }

    override fun mapToObj(response: JSONObject): TokenPair {
        TODO("Not yet implemented")
    }
}