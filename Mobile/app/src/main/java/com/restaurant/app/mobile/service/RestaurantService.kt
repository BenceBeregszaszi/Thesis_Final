package com.restaurant.app.mobile.service

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.restaurant.app.mobile.common.MapResponseToObj
import com.restaurant.app.mobile.common.ResponseToObjectList
import com.restaurant.app.mobile.common.Service
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.Restaurant
import org.json.JSONArray
import org.json.JSONObject

object RestaurantService : Service<Restaurant>(), MapResponseToObj<Restaurant>, ResponseToObjectList<Restaurant> {

    private val restaurantUrl: String = "$baseUrl/restaurants"

    override fun getListHttpRequest(context: Context, callback: VolleyCallback<Restaurant>) {
        JsonArrayRequest(Request.Method.GET, restaurantUrl, null,
            {
                    response -> val restaurants = convertResponseToObjList(response)
                                callback.onListSuccess(restaurants)
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun getHttpRequest(id: Long, context: Context, callback: VolleyCallback<Restaurant>) {
        JsonObjectRequest(Request.Method.GET, "$restaurantUrl/$id", null,
            {
                    response -> val restaurant = mapToObj(response)
                                callback.onSuccess(restaurant)
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun postHttpRequest(
        body: Restaurant,
        context: Context,
        callback: VolleyCallback<Restaurant>
    ) {
        JsonObjectRequest(Request.Method.POST, restaurantUrl, null,
            {
                    response -> val restaurant = mapToObj(response)
                                callback.onSuccess(restaurant)
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun putHttpRequest(
        id: Long,
        body: Restaurant,
        context: Context,
        callback: VolleyCallback<Restaurant>
    ) {
        JsonObjectRequest(Request.Method.PUT, "$restaurantUrl/$id", null,
            {
                    response -> val restaurant = mapToObj(response)
                                callback.onSuccess(restaurant)
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun deleteHttpRequest(
        id: Long,
        context: Context,
        callback: VolleyCallback<Restaurant>
    ) {
        JsonObjectRequest(Request.Method.DELETE, "$restaurantUrl/$id", null,
            {
                    callback.onDeleteSuccess()
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun mapToObj(response: JSONObject): Restaurant {
        val restaurant = Restaurant()
        restaurant.id = response.getLong("id")
        restaurant.name = response.getString("name")
        restaurant.maxSeatsNumber = response.getInt("maxSeatsNumber")
        //TODO: cities set
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