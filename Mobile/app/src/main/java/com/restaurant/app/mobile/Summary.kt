package com.restaurant.app.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Summary : AppCompatActivity() {

    private var btn_login: Button? = null
    private var btn_register: Button? = null
    private var btn_logout: Button? = null
    private var btn_settings: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)
    }

    override fun onResume() {
        super.onResume()
    }
}