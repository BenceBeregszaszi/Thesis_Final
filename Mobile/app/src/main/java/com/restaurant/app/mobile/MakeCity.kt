package com.restaurant.app.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.android.volley.VolleyError
import com.restaurant.app.mobile.adapters.SpinnerAdapter
import com.restaurant.app.mobile.common.Common
import com.restaurant.app.mobile.dto.City
import com.restaurant.app.mobile.dto.Restaurant
import com.restaurant.app.mobile.interfaces.Delete
import com.restaurant.app.mobile.interfaces.Success
import com.restaurant.app.mobile.interfaces.Error
import com.restaurant.app.mobile.interfaces.SpinnerProperty
import com.restaurant.app.mobile.service.CityService
import java.util.Objects
import java.util.stream.Collectors

class MakeCity : AppCompatActivity(), Success<City>, Delete, Error {

    private var city: City = City()
    private var restaurantObjects: ArrayList<SpinnerProperty> = ArrayList()
    private var restaurants: ArrayList<Long> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_city)

        val makeCity = findViewById<Button>(R.id.btn_make_city_save)
        val deleteCity = findViewById<Button>(R.id.btn_delete_city)
        val editCity = findViewById<Button>(R.id.btn_edit_city)
        val selectedRestaurant = findViewById<Spinner>(R.id.make_city_restaurants)
        val addRestaurant = findViewById<Button>(R.id.btn_add_restaurant)
        val backBtn = findViewById<Button>(R.id.make_city_back_btn)


        val data = intent.extras!!
        this.restaurantObjects = (data.get("restaurants") as ArrayList<SpinnerProperty>)
        selectedRestaurant.adapter = SpinnerAdapter(this.restaurantObjects, this)
        if (Objects.nonNull(data.get("city"))) {
            this.city = data.get("city") as City
            findViewById<EditText>(R.id.tb_postCode).setText(this.city.postCode)
            findViewById<EditText>(R.id.tb_cityName).setText(this.city.cityName)
            makeCity.visibility = View.GONE
            deleteCity.visibility = View.VISIBLE
            editCity.visibility = View.VISIBLE
        } else {
            makeCity.visibility = View.VISIBLE
            deleteCity.visibility = View.GONE
            editCity.visibility = View.GONE
        }

        addRestaurant.setOnClickListener {
            val item = selectedRestaurant.selectedItem as SpinnerProperty
            restaurants.add(item.getItemId())
            restaurantObjects.removeIf { restaurant -> restaurant.getItemId() == item.getItemId() }
            selectedRestaurant.adapter = SpinnerAdapter(this.restaurantObjects, this)
        }

        makeCity.setOnClickListener {
            this.city.postCode = findViewById<EditText>(R.id.tb_postCode).text.toString()
            this.city.cityName = findViewById<EditText>(R.id.tb_cityName).text.toString()
            this.city.restaurants.addAll(restaurants)
            CityService.postHttpRequest(this.city, this, this, this)
        }

        editCity.setOnClickListener {
            this.city.postCode = findViewById<EditText>(R.id.tb_postCode).text.toString()
            this.city.cityName = findViewById<EditText>(R.id.tb_cityName).text.toString()
            this.city.restaurants.addAll(restaurants)
            CityService.putHttpRequest(this.city.id, this.city, this, this, this)
        }

        deleteCity.setOnClickListener {
            CityService.deleteHttpRequest(this.city.id, this, this, this)
        }

        backBtn.setOnClickListener {
            finish()
        }
    }

    override fun onSuccess(result: City) {
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
            error.message?.let { Common.makeToastMessage(this, it) }
        }
    }
}