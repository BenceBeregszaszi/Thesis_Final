package com.restaurant.app.mobile.common

import org.json.JSONArray

interface ResponseToObjectList<T> {

    fun convertResponseToObjList(response: JSONArray) : ArrayList<T>
}