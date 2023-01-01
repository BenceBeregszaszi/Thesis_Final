package com.restaurant.app.mobile

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.restaurant.app.mobile.common.CommonProperties
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.City
import com.restaurant.app.mobile.dto.Reservation
import com.restaurant.app.mobile.dto.Restaurant
import com.restaurant.app.mobile.service.Authentication
import com.restaurant.app.mobile.service.ReservationService
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

class Summary : AppCompatActivity(), VolleyCallback<Reservation> {

    private var btn_login: Button? = null
    private var btn_register: Button? = null
    private var btn_logout: Button? = null
    private var btn_settings: Button? = null

    var city: City = City()
    var restaurant: Restaurant = Restaurant()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)
        getBtnInstances()
        if (CommonProperties.loggedIn){
            renderLogInHeader()
        } else renderLogoutHeader()

        if(intent.extras != null){
            this.city = intent.extras!!.get("city") as City
            this.restaurant = intent.extras!!.get("restaurant") as Restaurant
            findViewById<EditText>(R.id.tb_city_name).setText(this.city.cityName)
            findViewById<EditText>(R.id.tb_restaurant_name).setText(this.restaurant.name)
        } else {
            Toast.makeText(this, "No city or restaurant chosen", Toast.LENGTH_LONG)
        }


        findViewById<Button>(R.id.btn_save_summary).setOnClickListener {
            if (this.city.id == (0).toLong() || this.restaurant.id == (0).toLong()){
                Toast.makeText(this, "Cannot save because city or restaurant not chosen", Toast.LENGTH_LONG)
            } else {
                val tbSeatNumber = findViewById<EditText>(R.id.tb_max_seats_number)
                var count = 0
                try {
                    count = tbSeatNumber.text.toString().toInt()
                } catch (e: NumberFormatException){
                    Toast.makeText(this, "Incorrect value at number field!", Toast.LENGTH_LONG)
                }
                val datePicker = findViewById<DatePicker>(R.id.datePickerSummary)
                val today = Calendar.getInstance()
                var date = LocalDate.now()
                datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                    today.get(Calendar.DAY_OF_MONTH))
                    { _, year, month, day ->
                        date = LocalDate.of(year, month, day)
                    }
                if (count == 0){
                    Toast.makeText(this, "Wrong input value for seat number!", Toast.LENGTH_LONG)
                }
                else {
                    val reservation = Reservation()
                    reservation.userId = 4
                    reservation.cityId = this.city.id
                    reservation.restaurantId = this.restaurant.id
                    reservation.time = date
                    reservation.seatNumber = count
                    ReservationService.postHttpRequest(reservation, this, this)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (CommonProperties.loggedIn){
            renderLogInHeader()
        } else renderLogoutHeader()
    }

    private fun renderLogoutHeader(){
        getBtnInstances()
        btn_login?.visibility = View.VISIBLE
        btn_register?.visibility = View.VISIBLE
        btn_logout?.visibility = View.GONE
        btn_settings?.visibility = View.GONE
    }

    private fun renderLogInHeader(){
        getBtnInstances()
        btn_login?.visibility = View.GONE
        btn_register?.visibility = View.GONE
        btn_logout?.visibility = View.VISIBLE
        btn_settings?.visibility = View.VISIBLE
    }

    private fun getBtnInstances() {
        this.btn_login = findViewById(R.id.btn_login_summary)
        this.btn_logout = findViewById(R.id.btn_logout_summary)
        this.btn_register = findViewById(R.id.btn_register_summary)
        this.btn_settings = findViewById(R.id.btn_setting_summary)
    }

    override fun onSuccess(response: Reservation) {
        Toast.makeText(this, "Success reservation!", Toast.LENGTH_LONG)
        finish()
    }

    override fun onListSuccess(response: ArrayList<Reservation>) {
        TODO("Not yet implemented")
    }

    override fun onDeleteSuccess() {
        TODO("Not yet implemented")
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG)
    }
}