package com.restaurant.app.mobile.service

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.restaurant.app.mobile.common.*
import com.restaurant.app.mobile.dto.City
import com.restaurant.app.mobile.dto.ForgetPassword
import com.restaurant.app.mobile.dto.Restaurant
import com.restaurant.app.mobile.dto.User
import com.restaurant.app.mobile.interfaces.*
import org.json.JSONArray
import org.json.JSONObject


object UserService : ResponseToObjectList<User>, MapResponseToObj<User> {

    private val userUrl: String = "${Common.baseUrl}/users"

    fun getListHttpRequest(context: Context, callback: ListSuccess<User>, error: Error) {
        val request = JsonArrayRequest(Request.Method.GET, userUrl, null,
            {
                    response -> val users = convertResponseToObjList(response)
                                callback.onListSuccess(users)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun getListHttpRequest(context: Context, callback: MultipleRequestCallback<User, City, Restaurant>, error: Error) {
        val request = JsonArrayRequest(Request.Method.GET, userUrl, null,
            {
                    response -> val users = convertResponseToObjList(response)
                callback.onSuccessList(users)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun getHttpRequest(id: Long, context: Context, callback: Success<User> , error: Error) {
        val request = CustomJSONObjectRequest(Request.Method.GET, "$userUrl/$id", null,
            {
                    response -> val user = mapToObj(response)
                                callback.onSuccess(user)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun getUserByUsername(username: String, context: Context, callback: Success<User> , error: Error) {
        val request = CustomJSONObjectRequest(Request.Method.GET, "$userUrl/$username", null,
            {
                    response -> val user = mapToObj(response)
                                callback.onSuccess(user)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun postHttpRequest(body: User, context: Context, callback: Success<User> , error: Error) {
        val newObj = JSONObject()
        newObj.put("username", body.username)
        newObj.put("password", body.password)
        newObj.put("email", body.email)
        newObj.put("authority", Authority.USER)
        newObj.put("isDisabled", body.isDisabled)
        newObj.put("reminder", body.reminder)
        val request = CustomJSONObjectRequest(Request.Method.POST, "${Common.baseUrl}/authentication/register", newObj,
            {
                    callback.onSuccess(User())
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun putHttpRequest(
        id: Long,
        body: User,
        context: Context,
        callback: Success<User>,
        error: Error
    ) {
        val newObj = JSONObject()
        newObj.put("username", body.username)
        newObj.put("password", body.password)
        newObj.put("email", body.email)
        newObj.put("authority", body.authority)
        newObj.put("isDisabled", body.isDisabled)
        val request = CustomJSONObjectRequest(Request.Method.GET, "$userUrl/$id", newObj,
            {
                    response -> val user = mapToObj(response)
                                callback.onSuccess(user)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun forgetPassword(body: ForgetPassword, context: Context, callback: Success<User> , error: Error) {
        val newObj = JSONObject()
        newObj.put("email", body.email)
        newObj.put("newPassword", body.newPassword)
        newObj.put("reminder", body.reminder)
        val request = CustomJSONObjectRequest(Request.Method.PUT, "$userUrl/forget-password", newObj,
            {
                    callback.onSuccess(User())
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun deleteHttpRequest(id: Long, context: Context, callback: Delete, error: Error) {
        val request = CustomJSONObjectRequest(Request.Method.GET, "$userUrl/$id", null,
            {
                    callback.deleteSuccess()
            },
            {
                    volleyError -> error.error(volleyError)
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

    fun logout(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MainData", AppCompatActivity.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
        Common.accessToken = ""
        Common.refreshToken = ""
        Common.username = ""
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