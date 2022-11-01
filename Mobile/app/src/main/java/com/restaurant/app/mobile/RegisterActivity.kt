package com.restaurant.app.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.User
import com.restaurant.app.mobile.service.Authentication

class RegisterActivity : AppCompatActivity(), VolleyCallback<User> {

    private var register: Button? = null
    private var login: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        this.register = findViewById(R.id.btn_register)
        this.login = findViewById(R.id.toLogin_btn)

        this.login?.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        this.register?.setOnClickListener {
            val reminder: String = findViewById<EditText>(R.id.tb_reminder_reg).text.toString()
            val username:String = findViewById<EditText>(R.id.tb_username).text.toString()
            val password: String = findViewById<EditText>(R.id.tb_password).text.toString()
            val email: String = findViewById<EditText>(R.id.tb_email).text.toString()
            val user = User()
            user.username = username
            user.password = password
            user.email = email
            user.isDisabled = false
            user.reminder = reminder
            Authentication.register(user, this, this)
        }
    }

    override fun onSuccess(response: User) {
        finish()
    }

    override fun onListSuccess(response: ArrayList<User>) {
        TODO("Not yet implemented")
    }

    override fun onDeleteSuccess() {
        TODO("Not yet implemented")
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}