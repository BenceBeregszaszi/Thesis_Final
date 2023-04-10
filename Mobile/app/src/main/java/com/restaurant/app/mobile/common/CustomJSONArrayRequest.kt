package com.restaurant.app.mobile.common

import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException

class CustomJSONArrayRequest : JsonArrayRequest {

    private var headers: MutableMap<String, String>? = null

    override fun getHeaders(): MutableMap<String, String> {
        return headers ?: super.getHeaders()
    }

    constructor(url: String, headers: MutableMap<String, String>, responseListener: Response.Listener<JSONArray>, errorListener: Response.ErrorListener)
            : super(url, responseListener, errorListener) {
        this.headers = headers
    }

    override fun parseNetworkResponse(response: NetworkResponse?): Response<JSONArray> {
        try {
            val jsonString = response?.let { String(it.data, charset(HttpHeaderParser.parseCharset(response.headers))) }
            var result: JSONArray? = null

            if (jsonString != null && jsonString.isNotEmpty()){
                result = JSONArray(jsonString)
            }

            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response))
        } catch (e: UnsupportedEncodingException) {
            return Response.error(ParseError(e))
        } catch (je: JSONException) {
            return Response.error(ParseError(je))
        }
    }
}