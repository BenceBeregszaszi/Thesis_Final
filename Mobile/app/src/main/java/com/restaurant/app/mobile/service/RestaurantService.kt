package com.restaurant.app.mobile.service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.restaurant.app.mobile.common.*
import com.restaurant.app.mobile.dto.Restaurant
import org.json.JSONArray
import org.json.JSONObject

object RestaurantService : Service<Restaurant>(), MapResponseToObj<Restaurant>, ResponseToObjectList<Restaurant> {

    private val restaurantUrl: String = "$baseUrl/restaurants"

    override fun getListHttpRequest(context: Context, callback: VolleyCallback<Restaurant>) {
        val request = JsonArrayRequest(Request.Method.GET, restaurantUrl, null,
            {
                    response -> val restaurants = convertResponseToObjList(response)
                                callback.onListSuccess(restaurants)
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
    }

    override fun getHttpRequest(id: Long, context: Context, callback: VolleyCallback<Restaurant>) {
        val request = JsonObjectRequest(Request.Method.GET, "$restaurantUrl/$id", null,
            {
                    response -> val restaurant = mapToObj(response)
                                callback.onSuccess(restaurant)
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
    }

    override fun postHttpRequest(
        body: Restaurant,
        context: Context,
        callback: VolleyCallback<Restaurant>
    ) {
        val newObj = JSONObject()
        newObj.put("name", body.name)
        newObj.put("maySeatsNumber", body.maxSeatsNumber)
        newObj.put("address", body.address)
        newObj.put("cities", body.cities)
        val request = JsonObjectRequest(Request.Method.POST, restaurantUrl, newObj,
            {
                    response -> val restaurant = mapToObj(response)
                                callback.onSuccess(restaurant)
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
    }

    override fun putHttpRequest(
        id: Long,
        body: Restaurant,
        context: Context,
        callback: VolleyCallback<Restaurant>
    ) {
        val newObj = JSONObject()
        newObj.put("name", body.name)
        newObj.put("maySeatsNumber", body.maxSeatsNumber)
        newObj.put("address", body.address)
        newObj.put("cities", body.cities)
        val request = JsonObjectRequest(Request.Method.PUT, "$restaurantUrl/$id", null,
            {
                    response -> val restaurant = mapToObj(response)
                                callback.onSuccess(restaurant)
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
    }

    override fun deleteHttpRequest(
        id: Long,
        context: Context,
        callback: VolleyCallback<Restaurant>
    ) {
        val request = CustomJSONObjectRequest(Request.Method.DELETE, "$restaurantUrl/$id", null,
            {
                    callback.onDeleteSuccess()
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
    }

    override fun mapToObj(response: JSONObject): Restaurant {
        val restaurant = Restaurant()
        restaurant.id = response.getLong("id")
        restaurant.name = response.getString("name")
        restaurant.maxSeatsNumber = response.getInt("maxSeatsNumber")
        restaurant.address = response.getString("address")
        val set: JSONArray = response.getJSONArray("cities")
        for (i in 0 until set.length()) {
            restaurant.cities.add(set.get(i).toString().toLong())
        }
        return restaurant
    }

    override fun convertResponseToObjList(response: JSONArray): ArrayList<Restaurant> {
        val restaurants: ArrayList<Restaurant> = ArrayList()
        for (i in 0 until response.length()) {
            val responseObject = response.getJSONObject(i)
            val restaurant = mapToObj(responseObject)
            restaurants.add(restaurant)
        }

        return restaurants
    }
}