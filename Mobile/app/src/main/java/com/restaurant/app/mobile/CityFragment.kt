package com.restaurant.app.mobile


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.android.volley.VolleyError
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.restaurant.app.mobile.adapters.CityAdapter
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.City
import com.restaurant.app.mobile.service.CityService


class CityFragment : Fragment(), VolleyCallback<City> {
    private var cityList: ListView? = null
    private var add_city_flbtn: FloatingActionButton? = null
    private val SUCCESS_MESSAGE: String = "Successful operation!"

    private var cities: ArrayList<City> = ArrayList()
    private var index: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.add_city_flbtn = view.findViewById(R.id.float_btn_add)
        this.cityList = view.findViewById(R.id.city_list)

        CityService.getListHttpRequest(this.requireContext(),this)

        this.cityList?.setOnItemClickListener { _, _, position, _ ->
            val selectedCity = this.cityList?.adapter?.getItem(position) as City
            val  intent = Intent(this.requireContext(), Summary::class.java)
            intent.putExtra("city", selectedCity)
            startActivity(intent)
        }

        this.cityList?.setOnItemLongClickListener { _, _, position, _ ->
            val selectedCity = this.cityList?.adapter?.getItem(position) as City
            val intent = Intent(this.requireContext(), MakeCity::class.java)
            intent.putExtra("city", selectedCity)
            startActivity(intent)
            CityService.getListHttpRequest(this.requireContext(),this)
            return@setOnItemLongClickListener(true)
        }
        this.add_city_flbtn?.setOnClickListener {
            val intent = Intent(this.requireContext(), MakeCity::class.java)
            startActivity(intent)
            CityService.getListHttpRequest(this.requireContext(), this)
        }
    }

    override fun onSuccess(response: City) {
        this.cities.add(response)
        renderCityList()
        makeToastMessage(SUCCESS_MESSAGE)
    }

    override fun onListSuccess(response: ArrayList<City>) {
        this.cities = response
        renderCityList()
    }

    override fun onError(error: VolleyError) {
        error.message?.let { makeToastMessage(it) }
    }

    override fun onDeleteSuccess() {
        this.cities.removeAt(this.index)
        renderCityList()
        makeToastMessage(SUCCESS_MESSAGE)
    }

    private fun renderCityList() {
        val cityAdapter = CityAdapter(this.cities, this.requireContext())
        this.cityList?.adapter = cityAdapter
    }

    private fun makeToastMessage(message: String) {
        Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}