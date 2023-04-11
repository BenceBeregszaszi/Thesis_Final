package com.restaurant.app.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.android.volley.VolleyError
import com.restaurant.app.mobile.common.Common
import com.restaurant.app.mobile.dto.User
import com.restaurant.app.mobile.interfaces.Success
import com.restaurant.app.mobile.interfaces.Error
import com.restaurant.app.mobile.service.Authentication
import java.math.BigInteger
import java.security.MessageDigest

class RegisterActivity : AppCompatActivity(), Success<User>, Error {

    private var register: Button? = null
    private var login: Button? = null
    private var backBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        this.register = findViewById(R.id.btn_register)
        this.login = findViewById(R.id.toLogin_btn)
        this.backBtn = findViewById(R.id.register_back_btn)


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
            user.password = hashPassword(password)
            user.email = email
            user.isDisabled = false
            user.reminder = reminder
            Authentication.register(user, this, this, this)
        }

        this.backBtn?.setOnClickListener {
            finish()
        }
    }

    override fun onSuccess(result: User) {
        finish()
    }

    override fun error(error: VolleyError) {
        Common.makeToastMessage(this,error.message!!)
    }

    private fun hashPassword(password: String): String {
        val md5 = MessageDigest.getInstance("MD5")
        return BigInteger(1, md5.digest(password.toByteArray())).toString(16);
    }
}