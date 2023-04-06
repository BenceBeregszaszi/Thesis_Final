package com.restaurant.app.mobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.android.volley.VolleyError
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.restaurant.app.mobile.adapters.ReservationAdapter
import com.restaurant.app.mobile.common.Authority
import com.restaurant.app.mobile.common.Common
import com.restaurant.app.mobile.dto.*
import com.restaurant.app.mobile.interfaces.*
import com.restaurant.app.mobile.service.CityService
import com.restaurant.app.mobile.service.ReservationService
import com.restaurant.app.mobile.service.RestaurantService
import com.restaurant.app.mobile.service.UserService

class ReservationFragment : Fragment(), Success<Reservation>, ListSuccess<Reservation>, Delete, Error {

    private val SUCCESS_MESSAGE = "Success operation!"
    private var reservation_list: ListView? = null
    private var add_reservation_flbtn : FloatingActionButton? = null
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
        this.add_reservation_flbtn = view.findViewById(R.id.float_btn_add)
        if (Common.user?.authority == Authority.NON_USER){
            this.add_reservation_flbtn?.visibility = View.GONE
        }

        ReservationService.getListHttpRequest(this.requireContext(), this, this)

        add_reservation_flbtn?.setOnClickListener {
            val intent = Intent(this.requireContext(), Summary::class.java)
            startActivity(intent)
        }
    }

    companion object : MultipleRequestCallback<User, City, Restaurant>,  Error {

        private var usersList = ArrayList<User>()
        private var citiesList = ArrayList<City>()
        private var restaurantsList = ArrayList<Restaurant>()
        private lateinit var context: Context

        fun getData(context: Context) {
            this.context = context
            UserService.getListHttpRequest(context, this, this)
        }

        override fun onSuccessList(result: ArrayList<User>) {
            usersList = result
            CityService.getListHttpRequest(context, this, this)
        }

        override fun onSuccessListSecond(result: ArrayList<City>) {
            citiesList = result
            RestaurantService.getListHttpRequest(context, this, this)
        }

        override fun onSuccessListThird(result: ArrayList<Restaurant>) {
            restaurantsList = result
            if (areListsEmpty()) {
             return
            } else {
                Common.makeToastMessage(context, "Success operation!");
            }
        }

        override fun error(error: VolleyError) {
            error.message?.let { Common.makeToastMessage(context, it) }
        }

        private fun areListsEmpty() : Boolean {
            return restaurantsList.isEmpty() || usersList.isEmpty() || citiesList.isEmpty();
        }

        private fun renderReservationList(reservation_list: ListView?, reservations: ArrayList<Reservation>) {
            val representation = mapReservationToReservationRepresentation(reservations)
            val reservationAdapter = ReservationAdapter(representation, context)
            reservation_list?.adapter = reservationAdapter
        }

        private fun mapReservationToReservationRepresentation(reservations: ArrayList<Reservation>): List<ReservationRepresentation> {
            val representations: List<ReservationRepresentation> = ArrayList()
            reservations.forEach { reservation ->
                val representation = ReservationRepresentation()
                representation.id = reservation.id
                representation.time = reservation.time
                representation.seatNumber = reservation.seatNumber
                representation.city = findCityById(reservation.cityId)
                representation.user = findUserById(reservation.userId)
                representation.restaurant = findRestaurantById(reservation.restaurantId)
            }
            return representations
        }

        private fun findCityById(id: Long): String {
            var result = ""
            if (citiesList.isNotEmpty()) {
                result = citiesList[0].cityName
            }
            citiesList.forEach { city ->
                if (city.id == id) {
                    result = city.cityName
                }
            }
            return result
        }

        private fun findUserById(id: Long): String {
            var result = ""
            if (usersList.isNotEmpty()){
                result = usersList[0].username
            }
            usersList.forEach { user ->
                if(user.id == id) {
                    result = user.username
                }
            }
            return result
        }

        private fun findRestaurantById(id: Long): String {
            var result = ""
            if(restaurantsList.isNotEmpty()) {
                result = restaurantsList[0].name
            }
            restaurantsList.forEach { restaurant ->
                if (restaurant.id == id) {
                    result = restaurant.name
                }
            }
            return result
        }
    }

    override fun onSuccess(result: Reservation) {
        this.reservations.add(result)
        getData(this.requireContext())

    }

    override fun onListSuccess(result: ArrayList<Reservation>) {
        this.reservations = result
        renderReservationList(this.reservation_list, this.reservations)
    }

    override fun deleteSuccess() {
        this.reservations.removeAt(index)
        renderReservationList(this.reservation_list, this.reservations)
        Common.makeToastMessage(this.requireContext(), SUCCESS_MESSAGE)
    }

    override fun error(error: VolleyError) {
        if (error.networkResponse.statusCode == 401) {
            val intent = Intent(this.requireContext(), LoginActivity::class.java)
            startActivity(intent)
        } else {
            Common.makeToastMessage(this.requireContext(),error.message!!)
        }
    }
}