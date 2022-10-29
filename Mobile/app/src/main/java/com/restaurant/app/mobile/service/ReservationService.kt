package com.restaurant.app.mobile.service

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.restaurant.app.mobile.common.MapResponseToObj
import com.restaurant.app.mobile.common.ResponseToObjectList
import com.restaurant.app.mobile.common.Service
import com.restaurant.app.mobile.dto.Reservation
import org.json.JSONArray
import org.json.JSONObject

object ReservationService : Service<Reservation>(), MapResponseToObj<Reservation>, ResponseToObjectList<Reservation> {

    private val reservationUrl: String = "$baseUrl/reservations"

    override fun getListHttpRequest(): List<Reservation> {
        var reservations: List<Reservation> = mutableListOf()
        JsonArrayRequest(Request.Method.GET, reservationUrl, null,
            {
                    response -> reservations = convertResponseToObjList(response)
            },
            {
                    error -> Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
        return reservations
    }

    override fun getHttpRequest(id: Long): Reservation {
        var reservation = Reservation()
        JsonObjectRequest(Request.Method.GET, "$reservationUrl/$id", null,
            {
                    response -> reservation = mapToObj(response)
            },
            {
                    error -> Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
        return reservation
    }

    override fun postHttpRequest(body: Reservation): Reservation {
        var reservation = Reservation()
        JsonObjectRequest(Request.Method.POST, reservationUrl, body,
            {
                    response -> reservation = mapToObj(response)
            },
            {
                    error -> Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
        return reservation
    }

    override fun putHttpRequest(id: Long, body: Reservation): Reservation {
        var reservation = Reservation()
        JsonObjectRequest(Request.Method.PUT, "$reservationUrl/$id", body,
            {
                    response -> reservation = mapToObj(response)
            },
            {
                    error -> Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
        return reservation
    }

    override fun deleteHttpRequest(id: Long) {
        var reservation = Reservation()
        JsonObjectRequest(Request.Method.DELETE, "$reservationUrl/$id", null,
            {
            },
            {
                    error -> Log.d("TAG", "getListHttpRequest:".format(error.toString()))
            })
    }

    override fun mapToObj(response: JSONObject): Reservation {
        TODO("Not yet implemented")
    }

    override fun convertResponseToObjList(response: JSONArray): List<Reservation> {
        TODO("Not yet implemented")
    }
}