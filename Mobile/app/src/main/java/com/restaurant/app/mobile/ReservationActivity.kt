package com.restaurant.app.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.Reservation
import com.restaurant.app.mobile.dto.Restaurant

class ReservationActivity : AppCompatActivity(), VolleyCallback<Reservation> {

    private val SUCCESS_DELETE: String = "Successful delete!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
    }

    override fun onSuccess(response: Reservation) {
        TODO("Not yet implemented")
    }

    override fun onListSuccess(response: ArrayList<Reservation>) {
        TODO("Not yet implemented")
    }

    override fun onDeleteSuccess() {
        //this.restaurants.removeAt(this.index)
        //renderRestaurantList()
        Toast.makeText(this, SUCCESS_DELETE, Toast.LENGTH_SHORT)
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}