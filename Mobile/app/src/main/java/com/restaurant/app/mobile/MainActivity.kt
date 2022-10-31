package com.restaurant.app.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.restaurant.app.mobile.adapters.CityAdapter
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.City
import com.restaurant.app.mobile.service.CityService

class MainActivity : AppCompatActivity(), VolleyCallback<City> {

    private val SUCCESS_DELETE: String = "Successful delete!"

    private var cities: ArrayList<City> = ArrayList()
    private var index: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        CityService.getListHttpRequest(this,this)

    }

    override fun onSuccess(response: City) {
        TODO("Not yet implemented")
    }

    override fun onListSuccess(response: ArrayList<City>) {
        this.cities = response
        renderCityList()
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteSuccess() {
        this.cities.removeAt(this.index)
        Toast.makeText(this, SUCCESS_DELETE, Toast.LENGTH_SHORT)
    }


    private fun renderCityList() {
        val cityAdapter = CityAdapter(this.cities, this)
        val cityList: ListView = findViewById(R.id.city_list)
        cityList.adapter = cityAdapter
    }
}