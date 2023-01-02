package com.restaurant.app.mobile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.restaurant.app.mobile.R
import com.restaurant.app.mobile.dto.User

class UserAdapter() : BaseAdapter() {

    private var users: List<User> = ArrayList()

    private var context: Context? = null

    constructor(users: List<User>, context: Context) : this() {
        this.users = users
        this.context = context
    }

    override fun getCount(): Int {
        return this.users.size
    }

    override fun getItem(p0: Int): Any {
        return this.users[0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val newView: View
        if (p1 == null){
            val inflater: LayoutInflater = this.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            newView = inflater.inflate(R.layout.restaurant_list, p2, false)
        } else {
            newView = p1
        }
        val username: TextView = newView.findViewById(R.id.username_text_list)
        val email: TextView = newView.findViewById(R.id.email_text_list)
        username.text = this.users[p0].username
        email.text = this.users[p0].email
        return newView
    }
}