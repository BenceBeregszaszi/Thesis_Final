package com.restaurant.app.mobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import com.android.volley.VolleyError
import com.restaurant.app.mobile.common.Common
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.User
import com.restaurant.app.mobile.service.UserService


class SettingFragment : Fragment(), VolleyCallback<User> {

    var user: User? = null
    var save: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        UserService.getUserByUsername(Common.username, this.requireContext(), this)
        val tb_username : EditText = view.findViewById(R.id.settings_tb_username)
        val tb_password : EditText = view.findViewById(R.id.settings_tb_password)
        val tb_email : EditText = view.findViewById(R.id.settings_tb_email)
        val tb_reminder : EditText = view.findViewById(R.id.settings_tb_reminder)
        val sw_disabled : SwitchCompat = view.findViewById(R.id.sw_disabled)
        val btn_logout : Button = view.findViewById(R.id.btn_logout)
        val btn_save : Button = view.findViewById(R.id.btn_save)

        btn_logout.setOnClickListener {
            UserService.logout(this.requireContext())
        }

        btn_save.setOnClickListener {
            val user = User()
            user.username = tb_username.text.toString()
            user.password = tb_password.text.toString()
            user.email = tb_email.text.toString()
            user.isDisabled = sw_disabled.isChecked
            user.reminder = tb_reminder.text.toString()
            UserService.putHttpRequest(user.id, user, this.requireContext(), this)
        }
    }

    override fun onSuccess(response: User) {
        this.user = response
        render(this.requireView(), user!!)
    }

    override fun onListSuccess(response: ArrayList<User>) {
        return
    }

    override fun onDeleteSuccess() {
        return
    }

    override fun onError(error: VolleyError) {
        Toast.makeText(this.requireContext(), error.message, Toast.LENGTH_SHORT).show()
    }

    private fun render(view: View, user: User) {
        view.findViewById<EditText>(R.id.settings_tb_username).setText(user.username)
        view.findViewById<EditText>(R.id.settings_tb_password).setText(user.password)
        view.findViewById<EditText>(R.id.settings_tb_email).setText(user.email)
        view.findViewById<EditText>(R.id.settings_tb_reminder).setText(user.reminder)
        view.findViewById<SwitchCompat>(R.id.sw_disabled).isChecked = user.isDisabled
    }
}