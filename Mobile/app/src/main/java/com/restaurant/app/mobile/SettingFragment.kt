package com.restaurant.app.mobile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import com.android.volley.VolleyError
import com.restaurant.app.mobile.common.Common
import com.restaurant.app.mobile.dto.User
import com.restaurant.app.mobile.interfaces.Success
import com.restaurant.app.mobile.interfaces.Error
import com.restaurant.app.mobile.service.UserService
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Objects


class SettingFragment : Fragment(), Success<User>, Error {

    var save: Boolean = false
    private val SUCCESSFUL_MESSAGE = "Successful operation"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onResume() {
        super.onResume()
        Common.user?.let { render(this.requireView(), it)}
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tb_username : EditText = view.findViewById(R.id.settings_tb_username)
        val tb_password : EditText = view.findViewById(R.id.settings_tb_password)
        val tb_email : EditText = view.findViewById(R.id.settings_tb_email)
        val tb_reminder : EditText = view.findViewById(R.id.settings_tb_reminder)
        val sw_disabled : Switch = view.findViewById(R.id.sw_disabled)
        val btn_logout : Button = view.findViewById(R.id.btn_logout)
        val btn_save : Button = view.findViewById(R.id.btn_save)
        if(Objects.nonNull(Common.user)) {
            tb_username.setText(Common.user!!.username)
            tb_email.setText(Common.user!!.email)
            tb_reminder.setText(Common.user!!.reminder)
            sw_disabled.isChecked = Common.user!!.isDisabled
        } else {
            val intent = Intent(this.requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

        btn_logout.setOnClickListener {
            UserService.logout(this.requireContext())
            tb_username.setText("")
            tb_email.setText("")
            tb_reminder.setText("")
        }

        btn_save.setOnClickListener {
            val user = User()
            user.id = Common.user!!.id
            user.username = tb_username.text.toString()
            if(tb_password.text.isEmpty()) {
                user.password = Common.user!!.password
            } else {
                val hashedPassword = hashPassword(tb_password.text.toString())
                user.password = hashedPassword
            }
            user.email = tb_email.text.toString()
            user.isDisabled = sw_disabled.isChecked
            user.reminder = tb_reminder.text.toString()
            user.authorities.addAll(Common.user!!.authorities)
            UserService.putHttpRequest(user.id, user, this.requireContext(), this, this)
        }
    }

    override fun onSuccess(result: User) {
        Common.user = result
        Common.makeToastMessage(this.requireContext(), SUCCESSFUL_MESSAGE)
        render(this.requireView(), result)
    }

    override fun error(error: VolleyError) {
        if (error.networkResponse.statusCode == 401) {
            val intent = Intent(this.requireContext(), LoginActivity::class.java)
            startActivity(intent)
        } else {
            Common.makeToastMessage(this.requireContext(), error.message!!)
        }
    }

    private fun render(view: View, user: User) {
        view.findViewById<EditText>(R.id.settings_tb_username).setText(user.username)
        view.findViewById<EditText>(R.id.settings_tb_email).setText(user.email)
        view.findViewById<EditText>(R.id.settings_tb_reminder).setText(user.reminder)
        view.findViewById<Switch>(R.id.sw_disabled).isChecked = user.isDisabled
    }

    private fun hashPassword(password: String): String {
        val md5 = MessageDigest.getInstance("MD5")
        return BigInteger(1, md5.digest(password.toByteArray())).toString(16);
    }
}