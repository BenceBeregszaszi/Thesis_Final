package com.restaurant.app.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.restaurant.app.mobile.common.CommonProperties
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.AuthenticationRequest
import com.restaurant.app.mobile.dto.TokenPair
import com.restaurant.app.mobile.service.Authentication

class LoginActivity : AppCompatActivity(), VolleyCallback<TokenPair>{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login: Button = findViewById(R.id.login_btn)

        val forget_btn: Button = findViewById(R.id.forget_btn)

        login.setOnClickListener {
            val username = findViewById<EditText>(R.id.text_username)
            val password = findViewById<EditText>(R.id.text_password)
            val authRequest = AuthenticationRequest(username.text.toString(), password.text.toString())
            Authentication.logIn(authRequest, this)
        }

        forget_btn.setOnClickListener {
            val intent = Intent(this, ForgetPassword::class.java)
            startActivity(intent)
        }
    }

    override fun onSuccess(response: TokenPair) {
        CommonProperties.accessToken = response.accessToken
        CommonProperties.refreshToken = response.refreshToken
        CommonProperties.loggedIn = true
    }

    override fun onListSuccess(response: ArrayList<TokenPair>) {
        TODO("Not yet implemented")
    }

    override fun onDeleteSuccess() {
        TODO("Not yet implemented")
    }

    override fun onError(message: String) {
        TODO("Not yet implemented")
    }
}