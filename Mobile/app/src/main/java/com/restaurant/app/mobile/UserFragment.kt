package com.restaurant.app.mobile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.VolleyError
import com.restaurant.app.mobile.adapters.UserAdapter
import com.restaurant.app.mobile.common.Common
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.User
import com.restaurant.app.mobile.service.UserService

class UserFragment : Fragment(), VolleyCallback<User> {

    private var userList: ListView? = null
    private var userText: TextView? = null
    private var btnLogin: Button? = null
    private val SUCCESS_MESSAGE: String = "Successful operation!"

    private var users = ArrayList<User>()
    private var index = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userList = view.findViewById(R.id.users_list)
        userText = view.findViewById(R.id.usersTextView)
        btnLogin = view.findViewById(R.id.users_btn_login)

        if (Common.accessToken == ""){
            userList?.visibility = View.GONE
            userText?.visibility = View.VISIBLE
            btnLogin?.visibility = View.VISIBLE
        } else {
            userText?.visibility = View.GONE
            btnLogin?.visibility = View.GONE
            userList?.visibility = View.VISIBLE
            UserService.getListHttpRequest(this.requireContext(), this)
        }

        this.userList?.setOnItemClickListener { _, _, position, _ ->
            val selectedUser = this.userList?.adapter?.getItem(position) as User
            val intent = Intent(this.requireContext(), EditUser::class.java)
            intent.putExtra("user", selectedUser)
            startActivity(intent)
        }

        btnLogin?.setOnClickListener {
            val intent = Intent(this.requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        if (Common.accessToken == ""){
            userList?.visibility = View.GONE
            userText?.visibility = View.VISIBLE
            btnLogin?.visibility = View.VISIBLE
        } else {
            userText?.visibility = View.GONE
            btnLogin?.visibility = View.GONE
            userList?.visibility = View.VISIBLE
            UserService.getListHttpRequest(this.requireContext(), this)
        }
    }

    override fun onSuccess(response: User) {
        this.users.add(response)
        renderUsersList()
        makeToastMessage(SUCCESS_MESSAGE)
    }

    override fun onListSuccess(response: ArrayList<User>) {
        this.users = response
        renderUsersList()
    }

    override fun onDeleteSuccess() {
        this.users.removeAt(index)
        renderUsersList()
        makeToastMessage(SUCCESS_MESSAGE)
    }

    override fun onError(error: VolleyError) {
        error.message?.let { makeToastMessage(it) }
    }

    private fun renderUsersList() {
        val usersAdapter = UserAdapter(users, this.requireContext())
        this.userList?.adapter = usersAdapter
    }

    private fun makeToastMessage(message: String) {
        Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}