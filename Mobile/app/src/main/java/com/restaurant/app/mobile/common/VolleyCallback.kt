package com.restaurant.app.mobile.common

import com.android.volley.VolleyError

interface VolleyCallback<T> {

    fun onSuccess(response: T)

    fun onListSuccess(response: ArrayList<T>)

    fun onDeleteSuccess()

    fun onError(error: VolleyError)
}