package com.restaurant.app.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.android.volley.VolleyError
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.User
import com.restaurant.app.mobile.service.UserService

class EditUser : AppCompatActivity(), VolleyCallback<User> {

    var user: User? = null
    var save: Boolean = false
    private val SUCCESS_MESSAGE: String = "Successful operation!"

    var tb_username : EditText? = null
    var tb_password : EditText? = null
    var tb_email : EditText? = null
    var tb_reminder : EditText? = null
    var sw_disabled : Switch? = null
    var btn_save : Button? = null
    var btn_delete: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)

        if (intent.extras != null) {
            user = intent.extras!!.get("user") as User
        }

        tb_username  = findViewById(R.id.user_tb_username)
        tb_password = findViewById(R.id.user_tb_password)
        tb_email = findViewById(R.id.user_tb_email)
        tb_reminder = findViewById(R.id.user_tb_reminder)
        sw_disabled = findViewById(R.id.user_sw_disabled)
        btn_save = findViewById(R.id.user_btn_save)
        btn_delete = findViewById(R.id.user_btn_delete)

        btn_save?.setOnClickListener {
            this.user?.username = tb_username?.text.toString()
            this.user?.password = tb_password?.text.toString()
            this.user?.email = tb_email?.text.toString()
            this.user?.reminder = tb_reminder?.text.toString()
            this.user?.isDisabled = sw_disabled!!.isChecked
            UserService.putHttpRequest(this.user!!.id, this.user!!, this, this)
        }

        btn_delete?.setOnClickListener {
            UserService.deleteHttpRequest(user!!.id, this, this)
        }
    }

    override fun onSuccess(response: User) {
        makeToastMessage(SUCCESS_MESSAGE)
        finish()
    }

    override fun onListSuccess(response: ArrayList<User>) {
        return
    }

    override fun onDeleteSuccess() {
        makeToastMessage(SUCCESS_MESSAGE)
        finish()
    }

    override fun onError(error: VolleyError) {
        error.message?.let { makeToastMessage(it) }
    }

    private fun makeToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}