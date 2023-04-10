package com.restaurant.app.mobile.service

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.restaurant.app.mobile.common.Common
import com.restaurant.app.mobile.common.CustomJSONObjectRequest
import com.restaurant.app.mobile.interfaces.MapResponseToObj
import com.restaurant.app.mobile.dto.AuthenticationRequest
import com.restaurant.app.mobile.dto.TokenPair
import com.restaurant.app.mobile.dto.User
import com.restaurant.app.mobile.interfaces.Success
import com.restaurant.app.mobile.interfaces.Error
import org.json.JSONObject
import java.math.BigInteger
import java.security.MessageDigest

object Authentication : MapResponseToObj<TokenPair> {

    private const val authenticationUrl: String = "http://10.0.2.2:8080/authentication"

    fun logIn(body: AuthenticationRequest, context: Context, callback: Success<TokenPair>, error: Error){
        val newObj = JSONObject()
        newObj.put("username", body.username)
        newObj.put("password", hashPassword(body.password))
        val request = CustomJSONObjectRequest(Request.Method.POST, "$authenticationUrl/authenticate", Common.getHeaders(), newObj,
            {
                    response -> val tokens = mapToObj(response)
                                callback.onSuccess(tokens)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun refreshTokens(body: TokenPair, context: Context, callback: Success<TokenPair>, error: Error) {
        val newObj = JSONObject()
        newObj.put("refreshToken", body.refreshToken)
        newObj.put("accessToken", body.accessToken)
        val request = JsonObjectRequest(Request.Method.POST, "$authenticationUrl/authenticate", newObj,
            {
                    response -> val tokens = mapToObj(response)
                                    callback.onSuccess(tokens)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun register(body: User, context: Context, callback: Success<User>, error: Error) {
        UserService.postHttpRequest(body, context, callback, error)
    }


    override fun mapToObj(response: JSONObject): TokenPair {
        val tokens = TokenPair()
        tokens.accessToken = response.getString("accessToken")
        tokens.refreshToken = response.getString("refreshToken")
        return tokens
    }

    private fun hashPassword(password: String): String {
        val md5 = MessageDigest.getInstance("MD5")
        return BigInteger(1, md5.digest(password.toByteArray())).toString(16);
    }
}