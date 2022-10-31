package com.restaurant.app.mobile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.restaurant.app.mobile.R
import com.restaurant.app.mobile.dto.Restaurant

class RestaurantAdapter() : BaseAdapter() {

    private var restaurants: List<Restaurant> = mutableListOf()

    private var context: Context? = null

    constructor(restaurants: List<Restaurant>, context: Context) : this() {
        this.restaurants = restaurants
        this.context = context
    }

    override fun getCount(): Int {
       return this.restaurants.size
    }

    override fun getItem(p0: Int): Any {
        return this.restaurants[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val newView: View
        if (p1 == null){
            val inflater: LayoutInflater = this.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            newView = inflater.inflate(R.layout.restaurant_list, p2, false)
        } else {
            newView = p1
        }
        val name: TextView = newView.findViewById(R.id.restaurant_name)
        val seatsNumber: TextView = newView.findViewById(R.id.seats)
        name.text = this.restaurants[p0].name
        seatsNumber.text = this.restaurants[p0].maxSeatsNumber.toString()
        return newView
    }
}