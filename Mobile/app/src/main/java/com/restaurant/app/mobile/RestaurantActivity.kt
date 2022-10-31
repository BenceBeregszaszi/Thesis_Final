package com.restaurant.app.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.restaurant.app.mobile.adapters.RestaurantAdapter
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.Restaurant
import com.restaurant.app.mobile.service.RestaurantService

class RestaurantActivity : AppCompatActivity(), VolleyCallback<Restaurant> {

    private val SUCCESS_DELETE: String = "Successful delete!"

    private var restaurants: ArrayList<Restaurant> = ArrayList()
    private var index: Int = -1
    private var restaurantList: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        restaurantList = findViewById(R.id.restaurant_list)
        this.restaurants.clear()
        if (intent.extras != null) {
            val restaurants = intent.extras!!.getLongArray("restaurants")
            for (i in 0 until restaurants?.size!!){
                RestaurantService.getHttpRequest(restaurants[i], this, this)
            }
        } else {
            RestaurantService.getListHttpRequest(this, this)
        }
    }

    override fun onSuccess(response: Restaurant) {
        this.restaurants.add(response)
        this.renderRestaurantList()
    }

    override fun onListSuccess(response: ArrayList<Restaurant>) {
        this.restaurants = response
        this.renderRestaurantList()
    }

    override fun onDeleteSuccess() {
        this.restaurants.removeAt(this.index)
        renderRestaurantList()
        Toast.makeText(this, SUCCESS_DELETE, Toast.LENGTH_SHORT)
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun renderRestaurantList() {
        val restaurantAdapter = RestaurantAdapter(this.restaurants, this)
        this.restaurantList?.adapter = restaurantAdapter
    }
}