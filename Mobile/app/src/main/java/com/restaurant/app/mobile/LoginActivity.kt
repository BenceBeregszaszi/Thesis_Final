package com.restaurant.app.mobile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.android.volley.VolleyError
import com.auth0.android.jwt.JWT
import com.restaurant.app.mobile.common.Common
import com.restaurant.app.mobile.dto.AuthenticationRequest
import com.restaurant.app.mobile.dto.TokenPair
import com.restaurant.app.mobile.dto.User
import com.restaurant.app.mobile.interfaces.Success
import com.restaurant.app.mobile.interfaces.Error
import com.restaurant.app.mobile.service.Authentication

class LoginActivity : AppCompatActivity(), Success<TokenPair>, Error{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login: Button = findViewById(R.id.login_btn)

        val forgetBtn: Button = findViewById(R.id.forget_btn)

        val register: Button = findViewById(R.id.toRegister_btn)

        login.setOnClickListener {
            val username = findViewById<EditText>(R.id.text_username)
            val password = findViewById<EditText>(R.id.text_password)
            val authRequest = AuthenticationRequest()
            authRequest.username = username.text.toString()
            authRequest.password = password.text.toString()
            Authentication.logIn(authRequest,this,this, this)
        }

        register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        forgetBtn.setOnClickListener {
            val intent = Intent(this, ForgetPassword::class.java)
            startActivity(intent)
        }
    }

    override fun onSuccess(result: TokenPair) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("accessToken", result.accessToken)
            commit()
        }
        val jwt = JWT(result.accessToken)
        Common.user = jwt.getClaim("user").asObject(User().javaClass)
        Common.accessToken = result.accessToken
        Common.refreshToken = result.refreshToken
        finish()
    }

    override fun error(error: VolleyError) {
        error.message?.let { Common.makeToastMessage(this, it) }
    }
}