package com.restaurant.app.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.restaurant.app.mobile.common.CommonProperties
import com.restaurant.app.mobile.service.Authentication

class Summary : AppCompatActivity() {

    private var btn_login: Button? = null
    private var btn_register: Button? = null
    private var btn_logout: Button? = null
    private var btn_settings: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)
        getBtnInstances()
        if (CommonProperties.loggedIn){
            renderLogInHeader()
        } else renderLogoutHeader()


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
        this.btn_login = findViewById(R.id.btn_login_summary)
        this.btn_logout = findViewById(R.id.btn_logout_summary)
        this.btn_register = findViewById(R.id.btn_register_summary)
        this.btn_settings = findViewById(R.id.btn_setting_summary)
    }
}