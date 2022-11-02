package com.restaurant.app.mobile.service

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.restaurant.app.mobile.common.*
import com.restaurant.app.mobile.dto.Reservation
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date

object ReservationService : Service<Reservation>(), MapResponseToObj<Reservation>, ResponseToObjectList<Reservation> {

    private val reservationUrl: String = "$baseUrl/reservations"

    override fun getListHttpRequest(context: Context, callback: VolleyCallback<Reservation>) {
        val request = JsonArrayRequest(Request.Method.GET, reservationUrl, null,
            {
                    response -> val reservations = convertResponseToObjList(response)
                                callback.onListSuccess(reservations)
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
    }

    override fun getHttpRequest(id: Long, context: Context, callback: VolleyCallback<Reservation>) {
        val request = JsonObjectRequest(Request.Method.GET, "$reservationUrl/$id", null,
            {
                    response -> val reservation = mapToObj(response)
                                callback.onSuccess(reservation)
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
    }

    override fun postHttpRequest(
        body: Reservation,
        context: Context,
        callback: VolleyCallback<Reservation>
    ) {
        val newObj = JSONObject()
        newObj.put("cityId", body.cityId)
        newObj.put("userId", body.userId)
        newObj.put("restaurantId", body.restaurantId)
        newObj.put("seatNumber", body.seatNumber)
        newObj.put("time", body.time)
        val request = JsonObjectRequest(Request.Method.POST, reservationUrl, newObj,
            {
                    response -> val reservation = mapToObj(response)
                                callback.onSuccess(reservation)
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
    }

    override fun putHttpRequest(
        id: Long,
        body: Reservation,
        context: Context,
        callback: VolleyCallback<Reservation>
    ) {
        val newObj = JSONObject()
        newObj.put("cityId", body.cityId)
        newObj.put("restaurantId", body.restaurantId)
        newObj.put("seatNumber", body.seatNumber)
        newObj.put("time", body.time)
        val request = JsonObjectRequest(Request.Method.PUT, "$reservationUrl/$id", newObj,
            {
                    response -> val reservation = mapToObj(response)
                                callback.onSuccess(reservation)
            },
            {
                    error -> callback.onError(error.toString())
            })
        Volley.newRequestQueue(context).add(request)
    }

    override fun deleteHttpRequest(
        id: Long,
        context: Context,
        callback: VolleyCallback<Reservation>
    ) {
        val request = CustomJSONObjectRequest(Request.Method.DELETE, "$reservationUrl/$id", null,
            {
                    callback.onDeleteSuccess()
            },
            {
                    error -> callback.onError(error.toString())
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