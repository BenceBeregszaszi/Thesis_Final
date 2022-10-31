package com.restaurant.app.mobile.common

import android.content.Context

abstract class Service<T> {

    val baseUrl: String = "http://10.0.2.2:8080"

    abstract fun getListHttpRequest(context: Context, callback: VolleyCallback<T>)

    abstract fun getHttpRequest(id: Long, context: Context, callback: VolleyCallback<T>)

    abstract fun postHttpRequest(body: T, context: Context, callback: VolleyCallback<T>)

    abstract fun putHttpRequest(id: Long, body: T, context: Context, callback: VolleyCallback<T>)

     abstract fun deleteHttpRequest(id: Long, context: Context, callback: VolleyCallback<T>)
}