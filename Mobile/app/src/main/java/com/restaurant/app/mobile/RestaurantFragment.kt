package com.restaurant.app.mobile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.core.view.get
import com.android.volley.VolleyError
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.restaurant.app.mobile.adapters.RestaurantAdapter
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.City
import com.restaurant.app.mobile.dto.Restaurant
import com.restaurant.app.mobile.service.RestaurantService


class RestaurantFragment : Fragment(), VolleyCallback<Restaurant> {
    private val SUCCESS_MESSAGE: String = "Successful operation!"

    private var restaurants: ArrayList<Restaurant> = ArrayList()
    private var index: Int = -1
    private var restaurantList: ListView? = null
    private var add_restaurant_flbtn: FloatingActionButton? = null
    private var save: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.restaurantList = view.findViewById(R.id.restaurant_list)
        this.add_restaurant_flbtn = view.findViewById(R.id.float_btn_add)

        RestaurantService.getListHttpRequest(this.requireContext(), this)

        this.restaurantList?.setOnItemClickListener { _, _, position, _ ->
            val selectedRestaurant = this.restaurantList?.adapter?.getItem(position) as Restaurant
            val intent = Intent(this.requireContext(), Summary::class.java)
            intent.putExtra("restaurant", selectedRestaurant)
            startActivity(intent)
        }

        this.restaurantList?.setOnItemLongClickListener { _, _, position, _ ->
            val selectedRestaurant = this.restaurantList?.adapter?.getItem(position) as Restaurant
            val intent = Intent(this.requireContext(), MakeRestaurant::class.java)
            intent.putExtra("restaurant", selectedRestaurant)
            startActivity(intent)
            RestaurantService.getListHttpRequest(this.requireContext(), this)
            return@setOnItemLongClickListener(true)
        }

        this.add_restaurant_flbtn?.setOnClickListener {
            val intent = Intent(this.requireContext(), MakeRestaurant::class.java)
            startActivity(intent)
        }
    }

    override fun onSuccess(response: Restaurant) {
        this.restaurants.add(response)
        this.renderRestaurantList()
        makeToastMessage(SUCCESS_MESSAGE)
    }

    override fun onListSuccess(response: ArrayList<Restaurant>) {
        this.restaurants = response
        this.renderRestaurantList()
    }

    override fun onDeleteSuccess() {
        this.restaurants.removeAt(this.index)
        renderRestaurantList()
        makeToastMessage(SUCCESS_MESSAGE)
    }

    override fun onError(error: VolleyError) {
        error.message?.let { makeToastMessage(it) }
    }

    private fun renderRestaurantList() {
        val restaurantAdapter = RestaurantAdapter(this.restaurants, this.requireContext())
        this.restaurantList?.adapter = restaurantAdapter
    }

    private fun makeToastMessage(message: String) {
        Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}