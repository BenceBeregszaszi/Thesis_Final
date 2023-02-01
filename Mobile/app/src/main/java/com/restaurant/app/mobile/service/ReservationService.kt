package com.restaurant.app.mobile.service

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.restaurant.app.mobile.common.*
import com.restaurant.app.mobile.dto.Reservation
import com.restaurant.app.mobile.interfaces.*
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDate

object ReservationService : MapResponseToObj<Reservation>,
    ResponseToObjectList<Reservation> {

    private val reservationUrl: String = "${Common.baseUrl}/reservations"

    fun getListHttpRequest(context: Context, callback: ListSuccess<Reservation>, error: Error) {
        val request = JsonArrayRequest(Request.Method.GET, reservationUrl, null,
            {
                    response -> val reservations = convertResponseToObjList(response)
                                callback.onListSuccess(reservations)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun getHttpRequest(id: Long, context: Context, callback: Success<Reservation>, error: Error) {
        val request = CustomJSONObjectRequest(Request.Method.GET, "$reservationUrl/$id", null,
            {
                    response -> val reservation = mapToObj(response)
                                callback.onSuccess(reservation)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun postHttpRequest(
        body: Reservation,
        context: Context,
        callback: Success<Reservation>,
        error: Error
    ) {
        val newObj = JSONObject()
        newObj.put("cityId", body.cityId)
        newObj.put("userId", body.userId)
        newObj.put("restaurantId", body.restaurantId)
        newObj.put("seatNumber", body.seatNumber)
        newObj.put("time", body.time)
        val request = CustomJSONObjectRequest(Request.Method.POST, reservationUrl, newObj,
            {
                    response -> val reservation = mapToObj(response)
                                callback.onSuccess(reservation)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun putHttpRequest(
        id: Long,
        body: Reservation,
        context: Context,
        callback: Success<Reservation>,
        error: Error
    ) {
        val newObj = JSONObject()
        newObj.put("cityId", body.cityId)
        newObj.put("restaurantId", body.restaurantId)
        newObj.put("seatNumber", body.seatNumber)
        newObj.put("time", body.time)
        val request = CustomJSONObjectRequest(Request.Method.PUT, "$reservationUrl/$id", newObj,
            {
                    response -> val reservation = mapToObj(response)
                                callback.onSuccess(reservation)
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    fun deleteHttpRequest(
        id: Long,
        context: Context,
        callback: Delete,
        error: Error
    ) {
        val request = CustomJSONObjectRequest(Request.Method.DELETE, "$reservationUrl/$id", null,
            {
                    callback.deleteSuccess()
            },
            {
                    volleyError -> error.error(volleyError)
            })
        Volley.newRequestQueue(context).add(request)
    }

    override fun mapToObj(response: JSONObject): Reservation {
        val reservation = Reservation()
        reservation.id = response.getLong("id")
        reservation.cityId = response.getLong("cityId")
        reservation.restaurantId = response.getLong("restaurantId")
        reservation.time = LocalDate.parse(response.getString("time"))

        return reservation
    }

    override fun convertResponseToObjList(response: JSONArray): ArrayList<Reservation> {
        val reservations: ArrayList<Reservation> = ArrayList()
        for (i in 0 until response.length()) {
            val responseObject = response.getJSONObject(i)
            val reservation = mapToObj(responseObject)

            reservations.add(reservation)
        }

        return reservations
    }
}