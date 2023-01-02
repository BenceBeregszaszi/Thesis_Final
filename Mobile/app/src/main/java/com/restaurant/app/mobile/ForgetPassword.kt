package com.restaurant.app.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.VolleyError
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.ForgetPassword
import com.restaurant.app.mobile.dto.User
import com.restaurant.app.mobile.service.UserService

class ForgetPassword : AppCompatActivity(), VolleyCallback<User> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        val save = findViewById<Button>(R.id.btn_forget_password)
        val reminder = findViewById<EditText>(R.id.tb_reminder)
        val email = findViewById<EditText>(R.id.forget_tb_email)
        val password = findViewById<EditText>(R.id.forget_tb_password)
        val passwordAgain = findViewById<EditText>(R.id.forget_tb_passwordAgain)

        save.setOnClickListener {
            if (password.text == passwordAgain.text) {
                val forgetPasswordDto = ForgetPassword()
                forgetPasswordDto.reminder = reminder.text.toString()
                forgetPasswordDto.newPassword = password.text.toString()
                forgetPasswordDto.email = email.text.toString()

                UserService.forgetPassword(forgetPasswordDto, this, this)
            }
        }
    }

    override fun onSuccess(response: User) {
        finish()
    }

    override fun onListSuccess(response: ArrayList<User>) {
        return
    }

    override fun onDeleteSuccess() {
        return
    }

    override fun onError(error: VolleyError) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }
}