package com.restaurant.app.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.VolleyError
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.Restaurant
import com.restaurant.app.mobile.service.RestaurantService

class MakeRestaurant : AppCompatActivity(), VolleyCallback<Restaurant> {

    private var restaurant = Restaurant()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_restaurant)

        val makeRestaurant = findViewById<Button>(R.id.resta_btn_save)
        val editRestaurant = findViewById<Button>(R.id.resta_btn_edit)
        val deleteRestaurant = findViewById<Button>(R.id.resta_btn_delete)

        if (intent.extras != null){
            this.restaurant = intent.extras!!.get("restaurant") as Restaurant
            findViewById<EditText>(R.id.resta_tb_name).setText(restaurant.name)
            findViewById<EditText>(R.id.resta_tb_seat_number).setText(restaurant.maxSeatsNumber.toString())
            findViewById<EditText>(R.id.resta_tb_address).setText(restaurant.address)
            makeRestaurant.visibility = View.GONE
            deleteRestaurant.visibility = View.VISIBLE
            editRestaurant.visibility = View.VISIBLE
        } else {
            makeRestaurant.visibility = View.VISIBLE
            deleteRestaurant.visibility = View.GONE
            editRestaurant.visibility = View.GONE
        }

        makeRestaurant.setOnClickListener {
            this.restaurant.name = findViewById<EditText>(R.id.resta_tb_name).text.toString()
            this.restaurant.maxSeatsNumber = findViewById<EditText>(R.id.resta_tb_seat_number).text.toString().toInt()
            this.restaurant.address = findViewById<EditText>(R.id.resta_tb_address).text.toString()
            RestaurantService.postHttpRequest(this.restaurant, this, this)
        }

        editRestaurant.setOnClickListener {
            this.restaurant.name = findViewById<EditText>(R.id.resta_tb_name).text.toString()
            this.restaurant.maxSeatsNumber = findViewById<EditText>(R.id.resta_tb_seat_number).text.toString().toInt()
            this.restaurant.address = findViewById<EditText>(R.id.resta_tb_address).text.toString()
            RestaurantService.putHttpRequest(this.restaurant.id, this.restaurant, this, this)
        }

        deleteRestaurant.setOnClickListener {
            RestaurantService.deleteHttpRequest(this.restaurant.id, this, this)
        }
    }

    override fun onSuccess(response: Restaurant) {
        finish()
    }

    override fun onListSuccess(response: ArrayList<Restaurant>) {
        return
    }

    override fun onDeleteSuccess() {
        finish()
    }

    override fun onError(error: VolleyError) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }
}