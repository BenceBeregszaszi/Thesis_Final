package com.restaurant.app.mobile.service

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.restaurant.app.mobile.common.*
import com.restaurant.app.mobile.dto.City
import com.restaurant.app.mobile.dto.Restaurant
import com.restaurant.app.mobile.dto.User
import com.restaurant.app.mobile.interfaces.*
import org.json.JSONArray
import org.json.JSONObject

object RestaurantService : MapResponseToObj<Restaurant>,
    ResponseToObjectList<Restaurant> {

    private val restaurantUrl: String = "${Common.baseUrl}/restaurants"

    fun getListHttpRequest(context: Context, callback: ListSuccess<Restaurant>, error: Error) {
        val request = CustomJSONArrayRequest(restaurantUrl, Common.getHeaders(),
            {
                    response -> val restaurants = convertResponseToObjList(response)
                                callback.onListSuccess(restaurants)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun getListHttpRequest(context: Context, callback: MultipleRequestCallback<User, City, Restaurant>, error: Error) {
        val request = CustomJSONArrayRequest(restaurantUrl,  Common.getHeaders(),
            {
                    response -> val restaurants = convertResponseToObjList(response)
                callback.onSuccessListThird(restaurants)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun getHttpRequest(id: Long, context: Context, callback: Success<Restaurant>, error: Error) {
        val request = CustomJSONObjectRequest(Request.Method.GET, "$restaurantUrl/$id",  Common.getHeaders(), null,
            {
                    response -> val restaurant = mapToObj(response)
                                callback.onSuccess(restaurant)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun postHttpRequest(
        body: Restaurant,
        context: Context,
        callback: Success<Restaurant>,
        error: Error
    ) {
        val newObj = JSONObject()
        newObj.put("name", body.name)
        newObj.put("maxSeatsNumber", body.maxSeatsNumber)
        newObj.put("address", body.address)
        newObj.put("cities", body.cities)
        val request = CustomJSONObjectRequest(Request.Method.POST, restaurantUrl,  Common.getHeaders(), newObj,
            {
                    response -> val restaurant = mapToObj(response)
                                callback.onSuccess(restaurant)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun putHttpRequest(
        id: Long,
        body: Restaurant,
        context: Context,
        callback: Success<Restaurant>,
        error: Error
    ) {
        val newObj = JSONObject()
        newObj.put("name", body.name)
        newObj.put("maySeatsNumber", body.maxSeatsNumber)
        newObj.put("address", body.address)
        newObj.put("cities", body.cities)
        val request = CustomJSONObjectRequest(Request.Method.PUT, "$restaurantUrl/$id",  Common.getHeaders(), null,
            {
                    response -> val restaurant = mapToObj(response)
                                callback.onSuccess(restaurant)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun deleteHttpRequest(
        id: Long,
        context: Context,
        callback: Delete,
        error: Error
    ) {
        val request = CustomJSONObjectRequest(Request.Method.DELETE, "$restaurantUrl/$id",  Common.getHeaders(), null,
            {
                    callback.deleteSuccess()
            },
            {
                    volleyError -> error.error(volleyError)
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