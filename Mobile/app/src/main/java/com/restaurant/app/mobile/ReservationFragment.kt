package com.restaurant.app.mobile

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
import java.util.stream.Collectors

class ReservationFragment : Fragment(), ListSuccess<Reservation>, Error {

    private var reservation_list: ListView? = null
    private var add_reservation_flbtn : FloatingActionButton? = null
    private var reservations: ArrayList<Reservation> = ArrayList()
    private var citiesList = ArrayList<City>()
    private var restaurantsList = ArrayList<Restaurant>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reservation, container, false)
    }

    override fun onResume() {
        super.onResume()
        if(Common.user?.authorities?.contains(Authority.NON_USER) != true) {
            ReservationService.getListHttpRequest(this.requireContext(), this, this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.reservation_list = view.findViewById(R.id.reservation_list)
        this.add_reservation_flbtn = view.findViewById(R.id.reservation_add_flt_btn)

        if(Common.user?.authorities?.contains(Authority.NON_USER) != true) {
            ReservationService.getListHttpRequest(this.requireContext(), this, this)
        }

        add_reservation_flbtn?.setOnClickListener {
            val intent = Intent(this.requireContext(), Summary::class.java)
            intent.putExtra("cities", citiesList)
            intent.putExtra("restaurants", restaurantsList)
            startActivity(intent)
        }
    }

    override fun onListSuccess(result: ArrayList<Reservation>) {
        if(Common.user?.authorities?.contains(Authority.ADMIN) == true) {
            this.reservations = result
            getData(this)
        }
        if(Common.user?.authorities?.contains(Authority.USER) == true) {
            this.reservations =
                result.stream().filter { reservation -> reservation.userId == Common.user!!.id }.collect(Collectors.toList()) as ArrayList<Reservation>
            getData(this)
        }
    }

    override fun error(error: VolleyError) {
        if (error.networkResponse.statusCode == 401) {
            val intent = Intent(this.requireContext(), LoginActivity::class.java)
            startActivity(intent)
        } else {
            Common.makeToastMessage(this.requireContext(),error.message!!)
        }
    }

    companion object : MultipleRequestCallback<User, City, Restaurant>,  Error {

        private var usersList = ArrayList<User>()

        private lateinit var reservationFragment: ReservationFragment

        fun getData(reservationFragment: ReservationFragment) {
            this.reservationFragment = reservationFragment
            UserService.getListHttpRequest(this.reservationFragment.requireContext(), this, this)
        }

        override fun onSuccessList(result: ArrayList<User>) {
            usersList = result
            CityService.getListHttpRequest(this.reservationFragment.requireContext(), this, this)
        }

        override fun onSuccessListSecond(result: ArrayList<City>) {
            reservationFragment.citiesList = result
            RestaurantService.getListHttpRequest(this.reservationFragment.requireContext(), this, this)
        }

        override fun onSuccessListThird(result: ArrayList<Restaurant>) {
            reservationFragment.restaurantsList = result
            renderReservationList()
        }

        override fun error(error: VolleyError) {
            error.message?.let { Common.makeToastMessage(this.reservationFragment.requireContext(), it) }
        }

        private fun renderReservationList() {
            val representation = mapReservationToReservationRepresentation(this.reservationFragment.reservations)
            val reservationAdapter = ReservationAdapter(representation, this.reservationFragment.requireContext())
            this.reservationFragment.reservation_list?.adapter = reservationAdapter
        }

        private fun mapReservationToReservationRepresentation(reservations: ArrayList<Reservation>): List<ReservationRepresentation> {
            val representations: ArrayList<ReservationRepresentation> = ArrayList()
            reservations.forEach { reservation ->
                val representation = ReservationRepresentation()
                representation.id = reservation.id
                representation.time = reservation.time
                representation.seatNumber = reservation.seatNumber
                representation.city = findCityById(reservation.cityId)
                representation.user = findUserById(reservation.userId)
                representation.restaurant = findRestaurantById(reservation.restaurantId)
                representations.add(representation)
            }
            return representations
        }

        private fun findCityById(id: Long): String {
            var result = ""
            if (reservationFragment.citiesList.isNotEmpty()) {
                result = reservationFragment.citiesList[0].cityName
            }
            reservationFragment.citiesList.forEach { city ->
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
            if(reservationFragment.restaurantsList.isNotEmpty()) {
                result = reservationFragment.restaurantsList[0].name
            }
            reservationFragment.restaurantsList.forEach { restaurant ->
                if (restaurant.id == id) {
                    result = restaurant.name
                }
            }
            return result
        }
    }
}