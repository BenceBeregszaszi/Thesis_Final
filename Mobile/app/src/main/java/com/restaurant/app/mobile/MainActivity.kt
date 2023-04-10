package com.restaurant.app.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.restaurant.app.mobile.common.Common
import com.restaurant.app.mobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val sharedPref =  this.getPreferences(MODE_PRIVATE)
        Common.accessToken = sharedPref.getString("accessToken", "").toString()

        replaceFragment(CityFragment())

        binding!!.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.cities -> {
                    replaceFragment(CityFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.restaurants -> {
                    replaceFragment(RestaurantFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.reservations -> {
                    replaceFragment(ReservationFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.users -> {
                    replaceFragment(UserFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.settings -> {
                    replaceFragment(SettingFragment())
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }
}
