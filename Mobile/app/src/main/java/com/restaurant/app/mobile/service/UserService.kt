package com.restaurant.app.mobile.service

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.Volley
import com.restaurant.app.mobile.common.*
import com.restaurant.app.mobile.dto.ForgetPassword
import com.restaurant.app.mobile.dto.User
import org.json.JSONArray
import org.json.JSONObject


object UserService : Service<User>(), ResponseToObjectList<User>, MapResponseToObj<User> {

    private val userUrl: String = "$baseUrl/users"

    override fun getListHttpRequest(context: Context, callback: VolleyCallback<User>) {
        val request = JsonArrayRequest(Request.Method.GET, userUrl, null,
            {
                    response -> val users = convertResponseToObjList(response)
                                callback.onListSuccess(users)
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
    }

    override fun getHttpRequest(id: Long, context: Context, callback: VolleyCallback<User>) {
        val request = JsonObjectRequest(Request.Method.GET, "$userUrl/$id", null,
            {
                    response -> val user = mapToObj(response)
                                callback.onSuccess(user)
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
    }

    override fun postHttpRequest(body: User, context: Context, callback: VolleyCallback<User>) {
        val newObj = JSONObject()
        newObj.put("username", body.username)
        newObj.put("password", body.password)
        newObj.put("email", body.email)
        newObj.put("authority", Authority.USER)
        newObj.put("isDisabled", body.isDisabled)
        newObj.put("reminder", body.reminder)
        val request = CustomJSONObjectRequest(Request.Method.POST, "$baseUrl/authentication/register", newObj,
            {
                    callback.onSuccess(User())
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
    }

    override fun putHttpRequest(
        id: Long,
        body: User,
        context: Context,
        callback: VolleyCallback<User>
    ) {
        val newObj = JSONObject()
        newObj.put("username", body.username)
        newObj.put("password", body.password)
        newObj.put("email", body.email)
        newObj.put("authority", body.authority)
        newObj.put("isDisabled", body.isDisabled)
        val request = JsonObjectRequest(Request.Method.GET, "$userUrl/$id", newObj,
            {
                    response -> val user = mapToObj(response)
                                callback.onSuccess(user)
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun forgetPassword(id: Long, body: ForgetPassword, context: Context, callback: VolleyCallback<User>) {
        val newObj = JSONObject()
        newObj.put("email", body.email)
        newObj.put("newPassword", body.newPassword)
        newObj.put("reminder", body.reminder)
        val request = CustomJSONObjectRequest(Request.Method.PUT, "$userUrl/forget-password/$id", newObj,
            {
                    callback.onSuccess(User())
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
    }

    override fun deleteHttpRequest(id: Long, context: Context, callback: VolleyCallback<User>) {
        val request = CustomJSONObjectRequest(Request.Method.GET, "$userUrl/$id", null,
            {
                    callback.onDeleteSuccess()
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
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