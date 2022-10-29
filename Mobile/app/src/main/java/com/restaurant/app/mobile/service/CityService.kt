package com.restaurant.app.mobile.service

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.restaurant.app.mobile.common.MapResponseToObj
import com.restaurant.app.mobile.common.ResponseToObjectList
import com.restaurant.app.mobile.common.Service
import com.restaurant.app.mobile.dto.City
import org.json.JSONArray
import org.json.JSONObject

object CityService : Service<City>(), ResponseToObjectList<City>, MapResponseToObj<City>{

    private val cityUrl: String = "$baseUrl/cities"

    override fun getListHttpRequest(): List<City> {
        var cities: List<City> = mutableListOf()
        JsonArrayRequest(Request.Method.GET, cityUrl, null,
            {
                    response -> cities = convertResponseToObjList(response)
            },
            { error ->
                Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })

        return cities
    }

    override fun getHttpRequest(id: Long): City {
        var city = City()
        JsonObjectRequest(Request.Method.GET, "$cityUrl/$id", null,
            {
                    response -> city = mapToObj(response)
            },
            { error ->
                Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
        return city
    }

    override fun postHttpRequest(body: City): City {
        var city = City()
        JsonObjectRequest(Request.Method.POST, cityUrl, body,
            {
                    response -> city = mapToObj(response)
            },
            { error ->
                Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
        return city
    }

    override fun putHttpRequest(id: Long, body: City): City {
        var city = City()
        JsonObjectRequest(Request.Method.PUT, "$cityUrl/$id", body,
            {
                    response -> city = mapToObj(response)
            },
            { error ->
                Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
        return  city
    }

    override fun deleteHttpRequest(id: Long) {
        JsonObjectRequest(Request.Method.DELETE, "$cityUrl/$id", null,
            {
            },
            { error ->
                Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
    }

    override fun convertResponseToObjList(response: JSONArray): List<City> {
        var temp : List<City> = mutableListOf()
        Log.d("TAG", "getListHttpRequest:".format(response.toString()))
        return  temp
    }

    override fun mapToObj(response: JSONObject): City {
        var temp : City = City()
        Log.d("TAG", "getListHttpRequest:".format(response.toString()))
        return  temp
    }
}