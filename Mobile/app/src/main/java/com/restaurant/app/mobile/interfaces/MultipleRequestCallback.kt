package com.restaurant.app.mobile.interfaces

interface MultipleRequestCallback<U, C, R> {

    fun onSuccessList(result : ArrayList<U>)

    fun onSuccessListSecond(result : ArrayList<C>)

    fun onSuccessListThird(result : ArrayList<R>)
}