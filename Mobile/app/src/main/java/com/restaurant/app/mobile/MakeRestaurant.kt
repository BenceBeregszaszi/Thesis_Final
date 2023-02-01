package com.restaurant.app.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.android.volley.VolleyError
import com.restaurant.app.mobile.common.Common
import com.restaurant.app.mobile.dto.Restaurant
import com.restaurant.app.mobile.interfaces.Delete
import com.restaurant.app.mobile.interfaces.Success
import com.restaurant.app.mobile.interfaces.Error
import com.restaurant.app.mobile.service.RestaurantService

class MakeRestaurant : AppCompatActivity(), Success<Restaurant>, Delete,  Error{

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
            RestaurantService.postHttpRequest(this.restaurant, this, this, this)
        }

        editRestaurant.setOnClickListener {
            this.restaurant.name = findViewById<EditText>(R.id.resta_tb_name).text.toString()
            this.restaurant.maxSeatsNumber = findViewById<EditText>(R.id.resta_tb_seat_number).text.toString().toInt()
            this.restaurant.address = findViewById<EditText>(R.id.resta_tb_address).text.toString()
            RestaurantService.putHttpRequest(this.restaurant.id, this.restaurant, this, this, this)
        }

        deleteRestaurant.setOnClickListener {
            RestaurantService.deleteHttpRequest(this.restaurant.id, this, this, this)
        }
    }

    override fun onSuccess(result: Restaurant) {
        finish()
    }

    override fun deleteSuccess() {
        finish()
    }

    override fun error(error: VolleyError) {
        if (error.networkResponse.statusCode == 401) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            Common.makeToastMessage(this,error.message!!)
        }
    }
}