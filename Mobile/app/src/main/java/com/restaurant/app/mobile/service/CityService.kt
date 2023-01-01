package com.restaurant.app.mobile.service

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.restaurant.app.mobile.common.*
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
        val request = JsonObjectRequest(Request.Method.GET, "$cityUrl/$id", null,
            {
                    response -> val city = mapToObj(response)
                                callback.onSuccess(city)
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
    }

    override fun postHttpRequest(body: City, context: Context, callback: VolleyCallback<City>) {
        val newObj = JSONObject()
        newObj.put("postCode", body.postCode)
        newObj.put("cityName", body.cityName)
        if(body.restaurants.isEmpty()) {
            newObj.put("restaurants", "[6]")
        } else {
            newObj.put("restaurants", body.restaurants)
        }
        val request = object : JsonObjectRequest(Request.Method.POST, cityUrl, newObj,
            {
                    response -> val city = mapToObj(response)
                                callback.onSuccess(city)
            },
            {
                    error -> callback.onError(error.toString())
            })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Authorization"] = "Bearer " + CommonProperties.accessToken
                return headers
            }
        }
        Volley.newRequestQueue(context).add(request)
    }

    override fun putHttpRequest(
        id: Long,
        body: City,
        context: Context,
        callback: VolleyCallback<City>
    ) {
        val newObj = JSONObject()
        newObj.put("postCode", body.postCode)
        newObj.put("cityName", body.cityName)
        if(body.restaurants.isEmpty()) {
            newObj.put("restaurants", "[]")
        } else {
            newObj.put("restaurants", body.restaurants)
        }
        val request = object : JsonObjectRequest(Request.Method.PUT, "$cityUrl/$id", newObj,
            {
                    response -> val city = mapToObj(response)
                                callback.onSuccess(city)
            },
            {
                    error -> callback.onError(error.toString())
            })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Authorization"] = "Bearer " + CommonProperties.accessToken
                return headers
            }
        }
        Volley.newRequestQueue(context).add(request)
    }

    override fun deleteHttpRequest(id: Long, context: Context, callback: VolleyCallback<City>) {
        val request = object : CustomJSONObjectRequest(Request.Method.DELETE, "$cityUrl/$id", null,
            {
                    callback.onDeleteSuccess()
            },
            {
                    error -> callback.onError(error.toString())
            })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Authorization"] = "Bearer " + CommonProperties.accessToken
                return headers
            }
        }
        Volley.newRequestQueue(context).add(request)
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
        val set: JSONArray = response.getJSONArray("restaurants")
        for (i in 0 until set.length()) {
            city.restaurants.add(set.get(i).toString().toLong())
        }
        return city
    }
}