package com.restaurant.app.mobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.VolleyError
import com.restaurant.app.mobile.common.Common
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.AuthenticationRequest
import com.restaurant.app.mobile.dto.TokenPair
import com.restaurant.app.mobile.service.Authentication

class LoginActivity : AppCompatActivity(), VolleyCallback<TokenPair>{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login: Button = findViewById(R.id.login_btn)

        val forgetBtn: Button = findViewById(R.id.forget_btn)

        login.setOnClickListener {
            val username = findViewById<EditText>(R.id.text_username)
            val password = findViewById<EditText>(R.id.text_password)
            val authRequest = AuthenticationRequest()
            authRequest.username = username.text.toString()
            authRequest.password = password.text.toString()
            Authentication.logIn(authRequest,this,this)
        }

        forgetBtn.setOnClickListener {
            val intent = Intent(this, ForgetPassword::class.java)
            startActivity(intent)
        }
    }

    override fun onSuccess(response: TokenPair) {
        val sharedPref = getSharedPreferences("Login data", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("accessToken", response.accessToken)
        editor.putString("refreshToken", response.refreshToken)
        editor.apply()
        Common.username = findViewById<EditText>(R.id.text_username).text.toString()
        Common.accessToken = response.accessToken
        Common.refreshToken = response.refreshToken
        finish()
    }

    override fun onListSuccess(response: ArrayList<TokenPair>) {
        return
    }

    override fun onDeleteSuccess() {
        return
    }

    override fun onError(error: VolleyError) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }
}