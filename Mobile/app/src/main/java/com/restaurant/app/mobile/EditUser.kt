package com.restaurant.app.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import com.android.volley.VolleyError
import com.restaurant.app.mobile.common.Authority
import com.restaurant.app.mobile.common.Common
import com.restaurant.app.mobile.dto.User
import com.restaurant.app.mobile.interfaces.Delete
import com.restaurant.app.mobile.interfaces.Success
import com.restaurant.app.mobile.interfaces.Error
import com.restaurant.app.mobile.service.UserService
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Objects

class EditUser : AppCompatActivity(), Success<User>, Delete, Error {

    var id: Long = 0
    var authorities = ArrayList<Authority>()
    var save: Boolean = false
    private val SUCCESS_MESSAGE: String = "Successful operation!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)
        var tb_username: EditText  = findViewById(R.id.user_tb_username)
        val tb_password: EditText = findViewById(R.id.user_tb_password)
        val tb_email: EditText = findViewById(R.id.user_tb_email)
        val tb_reminder: EditText = findViewById(R.id.user_tb_reminder)
        val sw_disabled: Switch = findViewById(R.id.user_sw_disabled)
        val btn_save: Button = findViewById(R.id.user_btn_save)
        val btn_delete: Button = findViewById(R.id.user_btn_delete)
        val btn_back: Button = findViewById(R.id.edit_back_btn)

        var data = intent.extras
        if (Objects.nonNull(data)) {
            data = data!!
            val userToEdit = data.get("user") as User
            id = userToEdit.id
            authorities = userToEdit.authorities
            tb_username.setText(userToEdit.username)
            tb_email.setText(userToEdit.email)
            tb_reminder.setText(userToEdit.reminder)
            sw_disabled.isChecked = userToEdit.isDisabled
        }

        btn_save.setOnClickListener {
            val user = User()
            user.username = tb_username.text.toString()
            if(tb_password.text.isEmpty()) {
                user.password = Common.user!!.password
            } else {
                val hashedPassword = hashPassword(tb_password.text.toString())
                user.password = hashedPassword
            }
            user.email = tb_email.text.toString()
            user.reminder = tb_reminder.text.toString()
            user.isDisabled = sw_disabled.isChecked
            user.authorities = authorities
            UserService.putHttpRequest(id, user, this, this, this)
        }

        btn_delete.setOnClickListener {
            UserService.deleteHttpRequest(id, this, this, this)
        }

        btn_back.setOnClickListener {
            finish()
        }
    }

    override fun onSuccess(result: User) {
        Common.makeToastMessage(this, SUCCESS_MESSAGE)
        finish()
    }

    override fun deleteSuccess() {
        Common.makeToastMessage(this, SUCCESS_MESSAGE)
        finish()
    }

    override fun error(error: VolleyError) {
        if (error.networkResponse.statusCode == 401) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            Common.makeToastMessage(this,"Something went wrong!")
        }
    }

    private fun hashPassword(password: String): String {
        val md5 = MessageDigest.getInstance("MD5")
        return BigInteger(1, md5.digest(password.toByteArray())).toString(16);
    }
}