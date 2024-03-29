package com.restaurant.app.mobile.common

import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException

open class CustomJSONObjectRequest : JsonObjectRequest {

    private var headers: MutableMap<String, String>? = null

    constructor(method: Int,
                url: String,
                headers: MutableMap<String, String>?,
                body: JSONObject?,
                responseListener: Response.Listener<JSONObject>,
                errorListener: Response.ErrorListener) : super(method, url, body, responseListener, errorListener) {
                    this.headers = headers
    }

    override fun getHeaders(): MutableMap<String, String> {
        return headers ?: super.getHeaders()
    }

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
}