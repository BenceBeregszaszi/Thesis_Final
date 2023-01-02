package com.restaurant.app.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import com.android.volley.VolleyError
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.City
import com.restaurant.app.mobile.dto.Reservation
import com.restaurant.app.mobile.dto.Restaurant
import com.restaurant.app.mobile.service.ReservationService
import java.time.LocalDate
import java.util.Calendar

class Summary : AppCompatActivity(), VolleyCallback<Reservation> {

    var city: City = City()
    var restaurant: Restaurant = Restaurant()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)


        findViewById<Button>(R.id.btn_save_summary).setOnClickListener {
            if (this.city.id == (0).toLong() || this.restaurant.id == (0).toLong()){
                Toast.makeText(this, "Cannot save because city or restaurant not chosen", Toast.LENGTH_LONG).show()
            } else {
                val tbSeatNumber = findViewById<EditText>(R.id.tb_max_seats_number)
                var count = 0
                try {
                    count = tbSeatNumber.text.toString().toInt()
                } catch (e: NumberFormatException){
                    Toast.makeText(this, "Incorrect value at number field!", Toast.LENGTH_LONG).show()
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
                    Toast.makeText(this, "Wrong input value for seat number!", Toast.LENGTH_LONG).show()
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

    override fun onSuccess(response: Reservation) {
        Toast.makeText(this, "Success reservation!", Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onListSuccess(response: ArrayList<Reservation>) {
        return
    }

    override fun onDeleteSuccess() {
        return
    }

    override fun onError(error: VolleyError) {
        Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
    }
}