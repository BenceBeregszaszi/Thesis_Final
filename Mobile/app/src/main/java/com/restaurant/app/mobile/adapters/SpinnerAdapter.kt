package com.restaurant.app.mobile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.restaurant.app.mobile.R
import com.restaurant.app.mobile.interfaces.SpinnerProperty

class SpinnerAdapter(): BaseAdapter() {

    private var items: ArrayList<SpinnerProperty> = ArrayList()
    private var context: Context? = null

    constructor(items: ArrayList<SpinnerProperty>, context: Context) : this() {
        this.items = items
        this.context = context
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val newView: View
        if (convertView == null){
            val inflater: LayoutInflater = this.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            newView = inflater.inflate(R.layout.spinner_item, parent, false)
        } else {
            newView = convertView
        }
        val item: TextView = newView.findViewById(R.id.item)
        item.text = this.items[position].getItem()
        return newView
    }
}