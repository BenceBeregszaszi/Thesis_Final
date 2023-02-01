package com.restaurant.app.mobile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.restaurant.app.mobile.R
import com.restaurant.app.mobile.dto.Reservation
import com.restaurant.app.mobile.dto.ReservationRepresentation

class ReservationAdapter() : BaseAdapter() {

    private var reservations: List<ReservationRepresentation> = ArrayList()
    private var context: Context? = null

    constructor(reservations: List<ReservationRepresentation>, context: Context): this() {
        this.reservations = reservations
        this.context = context
    }

    override fun getCount(): Int {
        return this.reservations.size
    }

    override fun getItem(p0: Int): Any {
        return this.reservations[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val newView: View
        if (p1 == null){
            val inflater: LayoutInflater = this.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            newView = inflater.inflate(R.layout.reservation_list, p2, false)
        } else {
            newView = p1
        }
        return newView
    }
}