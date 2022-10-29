package com.restaurant.app.mobile.service

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.restaurant.app.mobile.common.MapResponseToObj
import com.restaurant.app.mobile.common.ResponseToObjectList
import com.restaurant.app.mobile.common.Service
import com.restaurant.app.mobile.dto.Restaurant
import org.json.JSONArray
import org.json.JSONObject

object RestaurantService : Service<Restaurant>(), MapResponseToObj<Restaurant>, ResponseToObjectList<Restaurant> {

    private val restaurantUrl: String = "$baseUrl/restaurants"

    override fun getListHttpRequest(): List<Restaurant> {
        var restaurants: List<Restaurant> = mutableListOf()
        JsonArrayRequest(Request.Method.GET, restaurantUrl, null,
            {
                    response -> restaurants = convertResponseToObjList(response)
            },
            {
                    error -> Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
        return restaurants
    }

    override fun getHttpRequest(id: Long): Restaurant {
        var restaurant = Restaurant()
        JsonObjectRequest(Request.Method.GET, "$restaurantUrl/$id", null,
            {
                    response -> restaurant = mapToObj(response)
            },
            {
                    error -> Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
        return restaurant
    }

    override fun postHttpRequest(body: Restaurant): Restaurant {
        var restaurant = Restaurant()
        JsonObjectRequest(Request.Method.POST, restaurantUrl, null,
            {
                    response -> restaurant = mapToObj(response)
            },
            {
                    error -> Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
        return restaurant
    }

    override fun putHttpRequest(id: Long, body: Restaurant): Restaurant {
        var restaurant = Restaurant()
        JsonObjectRequest(Request.Method.PUT, "$restaurantUrl/$id", null,
            {
                    response -> restaurant = mapToObj(response);
            },
            {
                    error -> Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
        return restaurant
    }

    override fun deleteHttpRequest(id: Long) {
        JsonObjectRequest(Request.Method.DELETE, "$restaurantUrl/$id", null,
            {
            },
            {
                    error -> Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
    }

    override fun mapToObj(response: JSONObject): Restaurant {
        TODO("Not yet implemented")
    }

    override fun convertResponseToObjList(response: JSONArray): List<Restaurant> {
        TODO("Not yet implemented")
    }
}