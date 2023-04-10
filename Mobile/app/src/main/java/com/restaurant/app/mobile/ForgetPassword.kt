package com.restaurant.app.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.android.volley.VolleyError
import com.restaurant.app.mobile.common.Common
import com.restaurant.app.mobile.dto.ForgetPassword
import com.restaurant.app.mobile.dto.User
import com.restaurant.app.mobile.interfaces.Success
import com.restaurant.app.mobile.interfaces.Error
import com.restaurant.app.mobile.service.UserService
import java.math.BigInteger
import java.security.MessageDigest

class ForgetPassword : AppCompatActivity(), Success<User>, Error {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        val save = findViewById<Button>(R.id.btn_forget_password)
        val reminder = findViewById<EditText>(R.id.tb_reminder)
        val email = findViewById<EditText>(R.id.forget_tb_email)
        val password = findViewById<EditText>(R.id.forget_tb_password)
        val passwordAgain = findViewById<EditText>(R.id.forget_tb_passwordAgain)

        save.setOnClickListener {
            if (password.text.toString() == passwordAgain.text.toString()) {
                val forgetPasswordDto = ForgetPassword()
                forgetPasswordDto.reminder = reminder.text.toString()
                forgetPasswordDto.newPassword = hashPassword(password.text.toString())
                forgetPasswordDto.email = email.text.toString()

                UserService.forgetPassword(forgetPasswordDto, this, this, this)
            }
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