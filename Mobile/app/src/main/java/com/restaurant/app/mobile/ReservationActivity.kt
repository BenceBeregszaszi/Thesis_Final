package com.restaurant.app.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.Reservation

class ReservationActivity : AppCompatActivity(), VolleyCallback<Reservation> {
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
        TODO("Not yet implemented")
    }

    override fun onError(message: String) {
        TODO("Not yet implemented")
    }
}