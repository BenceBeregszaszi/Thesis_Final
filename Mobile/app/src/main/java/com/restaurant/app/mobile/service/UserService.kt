package com.restaurant.app.mobile.service

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.restaurant.app.mobile.common.MapResponseToObj
import com.restaurant.app.mobile.common.ResponseToObjectList
import com.restaurant.app.mobile.common.Service
import com.restaurant.app.mobile.dto.User
import org.json.JSONArray
import org.json.JSONObject

object UserService : Service<User>(), ResponseToObjectList<User>, MapResponseToObj<User> {

    private val userUrl: String = "$baseUrl/users"

    override fun getListHttpRequest(): List<User> {
        var users: List<User> = mutableListOf()
        JsonArrayRequest(Request.Method.GET, userUrl, null,
            {
                    response -> users = convertResponseToObjList(response)
            },
            {
                    error -> Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
        return users
    }

    override fun getHttpRequest(id: Long): User {
        var user = User()
        JsonObjectRequest(Request.Method.GET, "$userUrl/$id", null,
            {
                    response -> user = mapToObj(response)
            },
            {
                    error -> Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
        return user
    }

    override fun postHttpRequest(body: User): User {
        var user = User()
        JsonObjectRequest(Request.Method.GET, userUrl, body,
            {
                    response -> user = mapToObj(response)
            },
            {
                    error -> Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
        return user
    }

    override fun putHttpRequest(id: Long, body: User): User {
        var user = User()
        JsonObjectRequest(Request.Method.GET, "$userUrl/$id", body,
            {
                    response -> user = mapToObj(response)
            },
            {
                    error -> Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
        return user
    }

    override fun deleteHttpRequest(id: Long) {
        JsonObjectRequest(Request.Method.GET, "$userUrl/$id", null,
            {
            },
            {
                    error -> Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
    }

    override fun convertResponseToObjList(response: JSONArray): List<User> {
        TODO("Not yet implemented")
    }

    override fun mapToObj(response: JSONObject): User {
        TODO("Not yet implemented")
    }
}