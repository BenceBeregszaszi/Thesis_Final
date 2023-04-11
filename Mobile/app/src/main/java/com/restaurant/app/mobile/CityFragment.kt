package com.restaurant.app.mobile


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.android.volley.VolleyError
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.restaurant.app.mobile.adapters.CityAdapter
import com.restaurant.app.mobile.common.Authority
import com.restaurant.app.mobile.common.Common
import com.restaurant.app.mobile.dto.City
import com.restaurant.app.mobile.dto.Restaurant
import com.restaurant.app.mobile.interfaces.Delete
import com.restaurant.app.mobile.interfaces.ListSuccess
import com.restaurant.app.mobile.interfaces.Success
import com.restaurant.app.mobile.interfaces.Error
import com.restaurant.app.mobile.service.CityService
import com.restaurant.app.mobile.service.RestaurantService
import java.util.Objects
import java.util.stream.Collectors


class CityFragment : Fragment(), Success<City>, ListSuccess<City>, Delete, Error {
    private var cityList: ListView? = null
    private var add_city_flbtn: FloatingActionButton? = null
    private val SUCCESS_MESSAGE: String = "Successful operation!"

    private var cities: ArrayList<City> = ArrayList()
    private var restaurants: ArrayList<Restaurant> = ArrayList()
    private var index: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.add_city_flbtn = view.findViewById(R.id.cities_add_flt_btn)
        if(Common.user?.authorities?.contains(Authority.ADMIN) != true) {
            add_city_flbtn?.visibility = View.GONE
        }
        this.cityList = view.findViewById(R.id.city_list)

        CityService.getListHttpRequest(this.requireContext(),this, this)
        getRestaurants(this)

        this.cityList?.setOnItemClickListener { _, _, position, _ ->
            val selectedCity = this.cityList?.adapter?.getItem(position) as City
            val intent = Intent(this.requireContext(), Summary::class.java)
            intent.putExtra("city", selectedCity)
            val selectedRestaurants = this.restaurants.stream().filter {
                    restaurant -> selectedCity.restaurants.contains(restaurant.id)
            }.collect(Collectors.toList()) as ArrayList
            intent.putExtra("restaurants", selectedRestaurants)
            startActivity(intent)
        }

        this.cityList?.setOnItemLongClickListener { _, _, position, _ ->
            val selectedCity = this.cityList?.adapter?.getItem(position) as City
            val intent = Intent(this.requireContext(), MakeCity::class.java)
            intent.putExtra("city", selectedCity)
            val restaurantsToCity = restaurants.stream()
                .filter { restaurant -> !restaurant.cities.contains(selectedCity.id) }
                .collect(Collectors.toList()) as ArrayList
            intent.putExtra("restaurants", restaurantsToCity)
            startActivity(intent)
            CityService.getListHttpRequest(this.requireContext(),this, this)
            return@setOnItemLongClickListener(true)
        }
        this.add_city_flbtn?.setOnClickListener {
            val intent = Intent(this.requireContext(), MakeCity::class.java)
            intent.putExtra("restaurants", this.restaurants)
            startActivity(intent)
            CityService.getListHttpRequest(this.requireContext(), this, this)
        }
    }

    override fun onSuccess(result: City) {
        this.cities.add(result)
        renderCityList()
        Common.makeToastMessage(this.requireContext(), SUCCESS_MESSAGE)
    }

    override fun onListSuccess(result: ArrayList<City>) {
        this.cities = result
        renderCityList()
    }

    override fun error(error: VolleyError) {
        if(error.networkResponse != null) {
            if(error.networkResponse.statusCode == 401) {
                val intent = Intent(this.requireContext(), LoginActivity::class.java)
                startActivity(intent)
                CityService.getListHttpRequest(this.requireContext(),this, this)
            }
        }
         else {
            Common.makeToastMessage(this.requireContext(),"Something went wrong!")
        }
    }

    override fun deleteSuccess() {
        this.cities.removeAt(this.index)
        renderCityList()
        Common.makeToastMessage(this.requireContext(), SUCCESS_MESSAGE)
    }

    private fun renderCityList() {
        if(isAdded) {
            val cityAdapter = CityAdapter(this.cities, this.requireContext())
            this.cityList?.adapter = cityAdapter
        }
    }

    companion object :  ListSuccess<Restaurant>, Error  {

        @SuppressLint("StaticFieldLeak")
        private lateinit var cityFragment: CityFragment

        fun getRestaurants(cityFragment: CityFragment) {
            this.cityFragment = cityFragment
            RestaurantService.getListHttpRequest(cityFragment.requireContext(), this, this)
        }

        override fun error(error: VolleyError) {
            error.message?.let { Common.makeToastMessage(cityFragment.requireContext(), it) }
        }

        override fun onListSuccess(result: ArrayList<Restaurant>) {
            cityFragment.restaurants.addAll(result);
        }
    }
}