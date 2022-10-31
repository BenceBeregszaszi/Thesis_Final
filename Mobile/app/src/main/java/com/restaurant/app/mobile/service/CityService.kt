package com.restaurant.app.mobile.service

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.restaurant.app.mobile.common.MapResponseToObj
import com.restaurant.app.mobile.common.ResponseToObjectList
import com.restaurant.app.mobile.common.Service
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.City
import org.json.JSONArray
import org.json.JSONObject

object CityService : Service<City>(), ResponseToObjectList<City>, MapResponseToObj<City>{

    private val cityUrl: String = "$baseUrl/cities"

    override fun getListHttpRequest(context: Context, callback: VolleyCallback<City>) {
        val request = JsonArrayRequest(Request.Method.GET, cityUrl, null,
            {
                    response -> val cities = convertResponseToObjList(response)
                                callback.onListSuccess(cities)
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
    }

    override fun getHttpRequest(id: Long, context: Context, callback: VolleyCallback<City>) {
        JsonObjectRequest(Request.Method.GET, "$cityUrl/$id", null,
            {
                    response -> val city = mapToObj(response)
                                callback.onSuccess(city)
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun postHttpRequest(body: City, context: Context, callback: VolleyCallback<City>) {
        JsonObjectRequest(Request.Method.POST, cityUrl, body,
            {
                    response -> val city = mapToObj(response)
                                callback.onSuccess(city)
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun putHttpRequest(
        id: Long,
        body: City,
        context: Context,
        callback: VolleyCallback<City>
    ) {
        JsonObjectRequest(Request.Method.PUT, "$cityUrl/$id", body,
            {
                    response -> val city = mapToObj(response)
                                callback.onSuccess(city)
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun deleteHttpRequest(id: Long, context: Context, callback: VolleyCallback<City>) {
        JsonObjectRequest(Request.Method.DELETE, "$cityUrl/$id", null,
            {
                    callback.onDeleteSuccess()
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun convertResponseToObjList(response: JSONArray): ArrayList<City> {
        val cities: ArrayList<City> = ArrayList()
        for (i in 0 until response.length()){
            val responseObject = response.getJSONObject(i)
            val city = mapToObj(responseObject)

            cities.add(city)
        }
        return cities
    }

    override fun mapToObj(response: JSONObject): City {
        val city = City()
        city.id = response.getLong("id")
        city.postCode = response.getString("postCode")
        city.cityName = response.getString("cityName")
        city.latitude = response.getDouble("latitude")
        city.longitude = response.getDouble("longitude")
        val set: List<String> = response.getString("restaurants").replace("[", "").replace("]", "").split(",")
        for (item in set) {
            city.restaurants.add(item.toLong())
        }
        return city
    }
}