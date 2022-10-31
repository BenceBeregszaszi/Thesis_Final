package com.restaurant.app.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.restaurant.app.mobile.adapters.RestaurantAdapter
import com.restaurant.app.mobile.adapters.UserAdapter
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.Restaurant
import com.restaurant.app.mobile.dto.User
import com.restaurant.app.mobile.service.UserService

class UsersActivity : AppCompatActivity(), VolleyCallback<User> {

    private val SUCCESS_DELETE: String = "Successful delete!"

    private var users: ArrayList<User> = ArrayList()
    private var index: Int = -1
    private var usersList: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        usersList = findViewById(R.id.users_list)
        UserService.getListHttpRequest(this, this)
    }

    override fun onSuccess(response: User) {
        this.users.add(response)
        this.renderUserList()
    }

    override fun onListSuccess(response: ArrayList<User>) {
        this.users = response
        this.renderUserList()
    }

    override fun onDeleteSuccess() {
        this.users.removeAt(this.index)
        renderUserList()
        Toast.makeText(this, SUCCESS_DELETE, Toast.LENGTH_SHORT)
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun renderUserList() {
        val userAdapter = UserAdapter(this.users, this)
        this.usersList?.adapter = userAdapter
    }
}