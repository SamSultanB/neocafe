package com.neocafe.neocafe.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.neocafe.neocafe.R

class SpinnerAdapter(val context: Context, val items: Array<SpinnerItem>) : BaseAdapter() {

    private var opened = false;

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
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false)

        val iconImageView: ImageView = view.findViewById(R.id.countryFlag)

        val item = items[position]
        iconImageView.setImageResource(item.iconResourceId)

        return view
    }


}
