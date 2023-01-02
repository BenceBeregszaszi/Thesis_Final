package com.restaurant.app.mobile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.android.volley.VolleyError
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.restaurant.app.mobile.adapters.ReservationAdapter
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.Reservation
import com.restaurant.app.mobile.service.ReservationService
import com.restaurant.app.mobile.service.RestaurantService

class ReservationFragment : Fragment(), VolleyCallback<Reservation> {

    private val SUCCESS_MESSAGE = "Success operation!"
    private var reservation_list: ListView? = null
    private var reservations: ArrayList<Reservation> = ArrayList()
    private var index = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reservation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.reservation_list = view.findViewById(R.id.reservation_list)

        ReservationService.getListHttpRequest(this.requireContext(), this)

        view.findViewById<FloatingActionButton>(R.id.float_btn_add).setOnClickListener {
            val intent = Intent(this.requireContext(), Summary::class.java)
            startActivity(intent)
        }
    }

    override fun onSuccess(response: Reservation) {
        this.reservations.add(response)
        makeToastMessage(SUCCESS_MESSAGE)
    }

    override fun onListSuccess(response: ArrayList<Reservation>) {
        this.reservations = response
    }

    override fun onDeleteSuccess() {
        this.reservations.removeAt(index)
        makeToastMessage(SUCCESS_MESSAGE)
    }

    override fun onError(error: VolleyError) {
        error.message?.let { makeToastMessage(it) }
    }

    private fun renderReservationList() {

    }

    private fun makeToastMessage(message: String) {
        Toast.makeText(this.requireContext(), message, Toast.LENGTH_LONG).show()
    }
}