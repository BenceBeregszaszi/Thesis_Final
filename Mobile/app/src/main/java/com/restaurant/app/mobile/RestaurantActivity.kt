package com.restaurant.app.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.Restaurant

class RestaurantActivity : AppCompatActivity(), VolleyCallback<Restaurant> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        Log.d("Restaurant", "Second app")
        if (intent.extras != null) {
            val restaurants = intent.extras!!.getLongArray("restaurants")
            Log.d("Restaurant", restaurants?.size.toString())
        }
    }

    override fun onSuccess(response: Restaurant) {
        TODO("Not yet implemented")
    }

    override fun onListSuccess(response: ArrayList<Restaurant>) {
        TODO("Not yet implemented")
    }

    override fun onDeleteSuccess() {
        TODO("Not yet implemented")
    }

    override fun onError(message: String) {
        TODO("Not yet implemented")
    }
}