package com.restaurant.app.mobile.service

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.restaurant.app.mobile.common.*
import com.restaurant.app.mobile.dto.User
import org.json.JSONArray
import org.json.JSONObject


object UserService : Service<User>(), ResponseToObjectList<User>, MapResponseToObj<User> {

    private val userUrl: String = "$baseUrl/users"

    override fun getListHttpRequest(context: Context, callback: VolleyCallback<User>) {
        JsonArrayRequest(Request.Method.GET, userUrl, null,
            {
                    response -> val users = convertResponseToObjList(response)
                                callback.onListSuccess(users)
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun getHttpRequest(id: Long, context: Context, callback: VolleyCallback<User>) {
        JsonObjectRequest(Request.Method.GET, "$userUrl/$id", null,
            {
                    response -> val user = mapToObj(response)
                                callback.onSuccess(user)
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun postHttpRequest(body: User, context: Context, callback: VolleyCallback<User>) {
        JsonObjectRequest(Request.Method.GET, userUrl, body,
            {
                    response -> val user = mapToObj(response)
                                callback.onSuccess(user)
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun putHttpRequest(
        id: Long,
        body: User,
        context: Context,
        callback: VolleyCallback<User>
    ) {
        JsonObjectRequest(Request.Method.GET, "$userUrl/$id", body,
            {
                    response -> val user = mapToObj(response)
                                callback.onSuccess(user)
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun deleteHttpRequest(id: Long, context: Context, callback: VolleyCallback<User>) {
        JsonObjectRequest(Request.Method.GET, "$userUrl/$id", null,
            {
                    callback.onDeleteSuccess()
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun convertResponseToObjList(response: JSONArray): ArrayList<User> {
        val users : ArrayList<User> = ArrayList()
        for (i in 0 until response.length()) {
            val responseObject = response.getJSONObject(i)
            val user = mapToObj(responseObject)
            users.add(user)
        }

        return users
    }

    override fun mapToObj(response: JSONObject): User {
        val user = User()
        user.id = response.getLong("id")
        user.username = response.getString("username")
        user.password = response.getString("password")
        user.email = response.getString("email")
        user.authority = Authority.valueOf(response.getString("authority"))
        user.isDisabled = response.getBoolean("isDisabled")
        return user
    }
}