package com.restaurant.app.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.android.volley.VolleyError
import com.restaurant.app.mobile.adapters.SpinnerAdapter
import com.restaurant.app.mobile.common.Common
import com.restaurant.app.mobile.dto.City
import com.restaurant.app.mobile.dto.Reservation
import com.restaurant.app.mobile.dto.Restaurant
import com.restaurant.app.mobile.interfaces.Success
import com.restaurant.app.mobile.interfaces.Error
import com.restaurant.app.mobile.interfaces.ListSuccess
import com.restaurant.app.mobile.interfaces.SpinnerProperty
import com.restaurant.app.mobile.service.CityService
import com.restaurant.app.mobile.service.ReservationService
import com.restaurant.app.mobile.service.RestaurantService
import java.time.LocalDate
import java.util.Calendar
import java.util.Objects
import java.util.stream.Collectors

class Summary : AppCompatActivity(), Success<Reservation>, ListSuccess<City>, Error {

    private var restaurants: ArrayList<Restaurant> = ArrayList()
    private var cities: ArrayList<City> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)
        val saveButton: Button = findViewById(R.id.btn_save_summary)
        val citiesSpinner: Spinner = findViewById(R.id.cities_spinner)
        val restaurantSpinner: Spinner = findViewById(R.id.restaurants_spinner)
        val tbSeatNumber: EditText = findViewById(R.id.tb_max_seats_number)
        val datePicker: DatePicker = findViewById(R.id.datePickerSummary)

        var data = intent.extras
        if(Objects.nonNull(data)) {
            data = data!!
            val selectedCity = data.get("city") as City?
            val selectedRestaurant = data.get("restaurant") as Restaurant?
            if (Objects.isNull(selectedCity) && Objects.isNull(selectedRestaurant)) {
                CityService.getListHttpRequest(this, this, this)
                getRestaurants(this)
            } else {
                if(Objects.nonNull(selectedCity)) {
                    if (selectedCity != null) {
                        cities.add(selectedCity)
                    }
                    restaurants.addAll(data.get("restaurants") as ArrayList<Restaurant>)
                } else {
                    if (selectedRestaurant != null) {
                        restaurants.add(selectedRestaurant)
                    }
                    cities.addAll(data.get("cities") as ArrayList<City>)
                }
            }
        }

        citiesSpinner.adapter = SpinnerAdapter(this.cities as ArrayList<SpinnerProperty>, this)
        restaurantSpinner.adapter = SpinnerAdapter(this.restaurants as ArrayList<SpinnerProperty>, this)

        saveButton.setOnClickListener {
            val reservation = Reservation()
            if (Objects.nonNull(Common.user)) {
                reservation.userId = Common.user!!.id
            } else {
                reservation.userId = 4
            }
            reservation.cityId = (citiesSpinner.selectedItem as SpinnerProperty).getItemId()
            reservation.restaurantId = (restaurantSpinner.selectedItem as SpinnerProperty).getItemId()
            reservation.time = getDateValue(datePicker)
            reservation.seatNumber = getCountValue(tbSeatNumber)
            if(reservation.seatNumber != 0) {
                ReservationService.postHttpRequest(reservation, this, this, this)
            } else {
                Common.makeToastMessage(this, "Incorrect value at number field!")
            }
        }

        citiesSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItemId: Long = (parent!!.selectedItem as SpinnerProperty).getItemId()
                val selectedRestaurants = this@Summary.restaurants.stream()
                    .filter { restaurant -> restaurant.cities.contains(selectedItemId) }.collect(Collectors.toList())
                restaurantSpinner.adapter = SpinnerAdapter(selectedRestaurants as ArrayList<SpinnerProperty>, this@Summary)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        restaurantSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItemId: Long = (parent!!.selectedItem as SpinnerProperty).getItemId()
                val selectedCities = this@Summary.cities.stream()
                    .filter { city -> city.restaurants.contains(selectedItemId) }.collect(Collectors.toList())
                citiesSpinner.adapter = SpinnerAdapter(selectedCities as ArrayList<SpinnerProperty>, this@Summary)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    override fun onSuccess(result: Reservation) {
        Common.makeToastMessage(this, "Success reservation!")
        finish()
    }

    override fun onListSuccess(result: ArrayList<City>) {
        cities.addAll(result)
    }

    override fun error(error: VolleyError) {
        if (error.networkResponse.statusCode == 401) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            Common.makeToastMessage(this,error.message!!)
        }
    }

    private fun getDateValue(datePicker: DatePicker): LocalDate {
        val today = Calendar.getInstance()
        var date = LocalDate.now()
        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH))
        { _, year, month, day ->
            date = LocalDate.of(year, month, day)
        }
        return date
    }

    private fun getCountValue(editText: EditText):Int {
        return try {
            editText.text.toString().toInt()
        } catch (e: NumberFormatException){
            Common.makeToastMessage(this, "Incorrect value at number field!")
            0
        }
    }

    companion object : ListSuccess<Restaurant>, Error {

        private lateinit var summary: Summary

        fun getRestaurants(summary: Summary) {
            this.summary = summary
            RestaurantService.getListHttpRequest(this.summary, this, this)
        }

        override fun error(error: VolleyError) {
            error.message?.let { Common.makeToastMessage(this.summary, it) }
        }

        override fun onListSuccess(result: ArrayList<Restaurant>) {
            this.summary.restaurants.addAll(result)
        }

    }
}