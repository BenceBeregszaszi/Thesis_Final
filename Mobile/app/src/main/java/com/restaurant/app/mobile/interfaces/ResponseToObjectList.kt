package com.restaurant.app.mobile.interfaces

import org.json.JSONArray

interface ResponseToObjectList<T> {

    fun convertResponseToObjList(response: JSONArray) : ArrayList<T>
}