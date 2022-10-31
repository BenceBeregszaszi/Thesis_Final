package com.restaurant.app.mobile.service

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.restaurant.app.mobile.common.MapResponseToObj
import com.restaurant.app.mobile.common.ResponseToObjectList
import com.restaurant.app.mobile.common.Service
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.Reservation
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date

object ReservationService : Service<Reservation>(), MapResponseToObj<Reservation>, ResponseToObjectList<Reservation> {

    private val reservationUrl: String = "$baseUrl/reservations"

    override fun getListHttpRequest(context: Context, callback: VolleyCallback<Reservation>) {
        JsonArrayRequest(Request.Method.GET, reservationUrl, null,
            {
                    response -> val reservations = convertResponseToObjList(response)
                                callback.onListSuccess(reservations)
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun getHttpRequest(id: Long, context: Context, callback: VolleyCallback<Reservation>) {
        JsonObjectRequest(Request.Method.GET, "$reservationUrl/$id", null,
            {
                    response -> val reservation = mapToObj(response)
                                callback.onSuccess(reservation)
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun postHttpRequest(
        body: Reservation,
        context: Context,
        callback: VolleyCallback<Reservation>
    ) {
        JsonObjectRequest(Request.Method.POST, reservationUrl, body,
            {
                    response -> val reservation = mapToObj(response)
                                callback.onSuccess(reservation)
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun putHttpRequest(
        id: Long,
        body: Reservation,
        context: Context,
        callback: VolleyCallback<Reservation>
    ) {
        JsonObjectRequest(Request.Method.PUT, "$reservationUrl/$id", body,
            {
                    response -> val reservation = mapToObj(response)
                                callback.onSuccess(reservation)
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun deleteHttpRequest(
        id: Long,
        context: Context,
        callback: VolleyCallback<Reservation>
    ) {
        JsonObjectRequest(Request.Method.DELETE, "$reservationUrl/$id", null,
            {
                    callback.onDeleteSuccess()
            },
            {
                    error -> callback.onError(error.toString())
            })
    }

    override fun mapToObj(response: JSONObject): Reservation {
        val reservation = Reservation()
        reservation.id = response.getLong("id")
        reservation.cityId = response.getLong("cityId")
        reservation.restaurantId = response.getLong("restaurantId")
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        reservation.time = formatter.parse(response.getString("time"))

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