package com.restaurant.app.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.restaurant.app.mobile.adapters.CityAdapter
import com.restaurant.app.mobile.common.CommonProperties
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.City
import com.restaurant.app.mobile.service.Authentication
import com.restaurant.app.mobile.service.CityService

class MainActivity : AppCompatActivity(), VolleyCallback<City> {

    private val SUCCESS_DELETE: String = "Successful delete!"

    private var cities: ArrayList<City> = ArrayList()
    private var index: Int = -1
    private var cityList: ListView? = null

    private var btn_login: Button? = null
    private var btn_register: Button? = null
    private var btn_logout: Button? = null
    private var btn_settings: Button? = null
    private var btn_add_city: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getBtnInstances()
        this.cityList = findViewById(R.id.city_list)
        if (CommonProperties.loggedIn){
            renderLogInHeader()
        } else renderLogoutHeader()


        CityService.getListHttpRequest(this,this)

        this.cityList?.setOnItemClickListener { _, _, position, _ ->
            val selectedCity = this.cityList?.adapter?.getItem(position) as City
            val intent = Intent(this, RestaurantActivity::class.java)
            intent.putExtra("city", selectedCity)
            startActivity(intent)
        }

        this.cityList?.setOnItemLongClickListener { _, _, position, _ ->
            val selectedCity = this.cityList?.adapter?.getItem(position) as City
            val intent = Intent(this, MakeCity::class.java)
            intent.putExtra("city", selectedCity)
            startActivity(intent)
            renderCityList()
            CityService.getListHttpRequest(this,this)
            return@setOnItemLongClickListener(true)
        }

        btn_login?.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btn_register?.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btn_logout?.setOnClickListener {
            Authentication.logOut()
            if (!CommonProperties.loggedIn) {
                renderLogoutHeader()
            }
        }
        btn_settings?.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        btn_add_city?.setOnClickListener{
            val intent = Intent(this, MakeCity::class.java)
            startActivity(intent)
            CityService.getListHttpRequest(this,this)
        }
    }

    override fun onResume() {
        super.onResume()
        if (CommonProperties.loggedIn){
            renderLogInHeader()
        }
        else renderLogoutHeader()
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
        renderCityList()
        Toast.makeText(this, SUCCESS_DELETE, Toast.LENGTH_SHORT)
    }


    private fun renderCityList() {
        val cityAdapter = CityAdapter(this.cities, this)
        this.cityList?.adapter = cityAdapter
    }

    private fun renderLogoutHeader(){
        getBtnInstances()
        btn_login?.visibility = View.VISIBLE
        btn_register?.visibility = View.VISIBLE
        btn_logout?.visibility = View.GONE
        btn_settings?.visibility = View.GONE
    }

    private fun renderLogInHeader(){
        getBtnInstances()
        btn_login?.visibility = View.GONE
        btn_register?.visibility = View.GONE
        btn_logout?.visibility = View.VISIBLE
        btn_settings?.visibility = View.VISIBLE
    }

    private fun getBtnInstances() {
        btn_login = findViewById(R.id.btn_login_main)
        btn_register = findViewById(R.id.btn_register_main)
        btn_logout = findViewById(R.id.btn_logout_main)
        btn_settings = findViewById(R.id.btn_settings_main)
        btn_add_city = findViewById(R.id.float_btn_addCity)
    }
}
