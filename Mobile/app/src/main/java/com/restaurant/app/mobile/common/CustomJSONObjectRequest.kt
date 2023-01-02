package com.restaurant.app.mobile.common

import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException

open class CustomJSONObjectRequest(method: Int,
                                   url: String,
                                   body: JSONObject?,
                                   responseListener: Response.Listener<JSONObject>,
                                   errorListener: Response.ErrorListener)
    : JsonObjectRequest(method, url, body, responseListener, errorListener) {


    override fun parseNetworkResponse(response: NetworkResponse?): Response<JSONObject> {
        try {
            val jsonString = response?.let { String(it.data, charset(HttpHeaderParser.parseCharset(response.headers))) }
            var result: JSONObject? = null

            if (jsonString != null && jsonString.isNotEmpty()){
                result = JSONObject(jsonString)
            }

            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response))
        } catch (e: UnsupportedEncodingException) {
            return Response.error(ParseError(e))
        } catch (je: JSONException) {
            return Response.error(ParseError(je))
        }
    }

    override fun getHeaders(): MutableMap<String, String> {
        val headers = HashMap<String, String>()
        headers["Content-Type"] = "application/json"
        headers["Authorization"] = "Bearer " + Common.accessToken
        return headers
    }

}