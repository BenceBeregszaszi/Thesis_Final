package com.restaurant.app.mobile.service

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.restaurant.app.mobile.common.*
import com.restaurant.app.mobile.dto.City
import com.restaurant.app.mobile.dto.Restaurant
import com.restaurant.app.mobile.dto.User
import com.restaurant.app.mobile.interfaces.*
import org.json.JSONArray
import org.json.JSONObject

object CityService :  ResponseToObjectList<City>, MapResponseToObj<City> {

    private val cityUrl: String = "${Common.baseUrl}/cities"

    fun getListHttpRequest(context: Context, callback: ListSuccess<City>, error: Error) {
        val request = JsonArrayRequest(Request.Method.GET, cityUrl, null,
            {
                    response -> val cities = convertResponseToObjList(response)
                                callback.onListSuccess(cities)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun getListHttpRequest(context: Context, callback: MultipleRequestCallback<User, City, Restaurant>,error: Error) {
        val request = JsonArrayRequest(Request.Method.GET, cityUrl, null,
            {
                    response -> val cities = convertResponseToObjList(response)
                callback.onSuccessListSecond(cities)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun getHttpRequest(id: Long, context: Context, callback: Success<City>, error: Error) {
        val request = CustomJSONObjectRequest(Request.Method.GET, "$cityUrl/$id",  Common.getHeaders(),null,
            {
                    response -> val city = mapToObj(response)
                                callback.onSuccess(city)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun postHttpRequest(body: City, context: Context, callback: Success<City>, error: Error) {
        val newObj = JSONObject()
        newObj.put("postCode", body.postCode)
        newObj.put("cityName", body.cityName)
        if(body.restaurants.isEmpty()) {
            newObj.put("restaurants", "[6]")
        } else {
            newObj.put("restaurants", body.restaurants)
        }
        val request = object : CustomJSONObjectRequest(Method.POST, cityUrl,  Common.getHeaders(), newObj,
            {
                    response -> val city = mapToObj(response)
                                callback.onSuccess(city)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        {
        }
        Volley.newRequestQueue(context).add(request)
    }

    fun putHttpRequest(
        id: Long,
        body: City,
        context: Context,
        callback: Success<City>, error: Error
    ) {
        val newObj = JSONObject()
        newObj.put("postCode", body.postCode)
        newObj.put("cityName", body.cityName)
        newObj.put("restaurants", body.restaurants)
        if(body.restaurants.isEmpty()) {
            newObj.put("restaurants", HashSet<Long>())
        } else {
            newObj.put("restaurants", body.restaurants)
        }
        val request = object : CustomJSONObjectRequest(Method.PUT, "$cityUrl/$id",  Common.getHeaders(), newObj,
            {
                    response -> val city = mapToObj(response)
                                callback.onSuccess(city)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        {
        }
        Volley.newRequestQueue(context).add(request)
    }

    fun deleteHttpRequest(id: Long, context: Context, callback: Delete, error: Error) {
        val request = object : CustomJSONObjectRequest(Method.DELETE, "$cityUrl/$id",  Common.getHeaders(), null,
            {
                    callback.deleteSuccess()
            },
            {
                    volleyError -> error.error(volleyError)
            })
        {
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