package com.restaurant.app.mobile.common

interface VolleyCallback<T> {

    fun onSuccess(response: T)

    fun onListSuccess(response: ArrayList<T>)

    fun onDeleteSuccess()

    fun onError(message: String)
}