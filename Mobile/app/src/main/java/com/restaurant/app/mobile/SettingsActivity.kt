package com.restaurant.app.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.restaurant.app.mobile.common.VolleyCallback
import com.restaurant.app.mobile.dto.User

class SettingsActivity : AppCompatActivity(), VolleyCallback<User>{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    override fun onSuccess(response: User) {
        TODO("Not yet implemented")
    }

    override fun onListSuccess(response: ArrayList<User>) {
        TODO("Not yet implemented")
    }

    override fun onDeleteSuccess() {
        TODO("Not yet implemented")
    }

    override fun onError(message: String) {
        TODO("Not yet implemented")
    }
}