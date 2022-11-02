package com.restaurant.app.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.restaurant.app.mobile.adapters.RestaurantAdapter
import com.restaurant.app.mobile.common.CommonProperties
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.City
import com.restaurant.app.mobile.dto.Restaurant
import com.restaurant.app.mobile.service.Authentication
import com.restaurant.app.mobile.service.RestaurantService

class RestaurantActivity : AppCompatActivity(), VolleyCallback<Restaurant> {

    private val SUCCESS_DELETE: String = "Successful delete!"

    private var restaurants: ArrayList<Restaurant> = ArrayList()
    private var index: Int = -1
    private var restaurantList: ListView? = null
    private var city: City = City()

    private var btn_login: Button? = null
    private var btn_register: Button? = null
    private var btn_logout: Button? = null
    private var btn_settings: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        restaurantList = findViewById(R.id.restaurant_list)
        this.restaurants.clear()
        getBtnInstances()

        if (CommonProperties.loggedIn){
            renderLogInHeader()
        }
        else renderLogoutHeader()

        if (intent.extras != null) {
            this.city = intent.extras!!.get("city") as City
            for (i in 0 until (this.city.restaurants.size)){
                RestaurantService.getHttpRequest(this.city.restaurants.elementAt(i), this, this)
            }
        } else {
            RestaurantService.getListHttpRequest(this, this)
        }

        this.restaurantList?.setOnItemClickListener { _, _, position, _ ->
            val selectedRestaurant = this.restaurantList?.adapter?.getItem(position) as Restaurant
            val intent = Intent(this, Summary::class.java)
            if (this.city.id != (0).toLong()) {
                intent.putExtra("city", this.city)
                this.city = City()
            }
            intent.putExtra("restaurant", selectedRestaurant)
            startActivity(intent)
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
    }

    override fun onResume() {
        super.onResume()
        if (CommonProperties.loggedIn){
            renderLogInHeader()
        } else renderLogoutHeader()
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
        btn_login = findViewById(R.id.btn_login_restaurant)
        btn_register = findViewById(R.id.btn_register_restaurant)
        btn_logout = findViewById(R.id.btn_logout_restaurant)
        btn_settings = findViewById(R.id.btn_settings_restaurant)
    }
}