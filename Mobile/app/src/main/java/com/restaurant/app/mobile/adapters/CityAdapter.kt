package com.restaurant.app.mobile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.restaurant.app.mobile.R
import com.restaurant.app.mobile.dto.City

class CityAdapter() : BaseAdapter() {

    private var cities : List<City> = ArrayList()
    private var context: Context? = null


    constructor(cities: List<City>, context: Context) : this() {
        this.cities = cities
        this.context = context
    }

    override fun getCount(): Int {
        return cities.size
    }

    override fun getItem(p0: Int): Any {
        return cities[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val newView: View
        if (p1 == null){
            val inflater: LayoutInflater = this.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            newView = inflater.inflate(R.layout.city_list, p2, false)
        } else {
            newView = p1
        }
        val postCode: TextView = newView.findViewById(R.id.post_code_text)
        val cityName: TextView = newView.findViewById(R.id.city_name_text)
        postCode.text = this.cities[p0].postCode
        cityName.text = this.cities[p0].cityName
        return newView
    }
}