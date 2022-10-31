package com.restaurant.app.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.restaurant.app.mobile.dto.AuthenticationRequest
import com.restaurant.app.mobile.service.Authentication

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login: Button = findViewById(R.id.login_btn)

        val forget_btn: Button = findViewById(R.id.forget_btn)

        login.setOnClickListener {
            val username = findViewById<EditText>(R.id.text_username)
            val password = findViewById<EditText>(R.id.text_password)
            val authRequest = AuthenticationRequest(username.text.toString(), password.text.toString())
            Authentication.logIn(authRequest)
        }

        forget_btn.setOnClickListener {
            //TODO: intent, start activity
        }
    }
}