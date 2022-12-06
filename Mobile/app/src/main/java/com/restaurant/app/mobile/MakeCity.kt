package com.restaurant.app.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.City
import com.restaurant.app.mobile.service.CityService

class MakeCity : AppCompatActivity(), VolleyCallback<City> {

    private var city: City = City()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_city)

        val makeCity = findViewById<Button>(R.id.btn_make_city_save)
        val deleteCity = findViewById<Button>(R.id.btn_delete_city)
        val editCity = findViewById<Button>(R.id.btn_edit_city)


        if (intent.extras != null) {
            this.city = intent.extras!!.get("city") as City
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

        makeCity.setOnClickListener {
            this.city.postCode = findViewById<EditText>(R.id.tb_postCode).text.toString()
            this.city.cityName = findViewById<EditText>(R.id.tb_cityName).text.toString()
            CityService.postHttpRequest(this.city, this, this)
        }

        editCity.setOnClickListener {
            this.city.postCode = findViewById<EditText>(R.id.tb_postCode).text.toString()
            this.city.cityName = findViewById<EditText>(R.id.tb_cityName).text.toString()
            CityService.putHttpRequest(this.city.id, this.city, this, this)
        }

        deleteCity.setOnClickListener {
            CityService.deleteHttpRequest(this.city.id, this, this)
        }
    }

    override fun onSuccess(response: City) {
        finish()
    }

    override fun onListSuccess(response: ArrayList<City>) {
        TODO("Not yet implemented")
    }

    override fun onDeleteSuccess() {
        finish()
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}