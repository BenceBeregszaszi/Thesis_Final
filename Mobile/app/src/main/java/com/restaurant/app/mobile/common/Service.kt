package com.restaurant.app.mobile.common

abstract class Service<T> {

    val baseUrl: String = "http://localhost:8080"

    abstract fun getListHttpRequest(): List<T>

    abstract fun getHttpRequest(id: Long): T

    abstract fun postHttpRequest(body: T) : T

    abstract fun putHttpRequest(id: Long, body: T): T

     abstract fun deleteHttpRequest(id: Long)
}