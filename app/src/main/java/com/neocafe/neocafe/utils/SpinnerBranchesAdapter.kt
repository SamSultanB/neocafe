package com.neocafe.neocafe.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.neocafe.neocafe.R
import com.neocafe.neocafe.entities.branches.Branche

class SpinnerBranchesAdapter(val context: Context, val items: List<Branche>) : BaseAdapter() {

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
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.rv_choose_filial_item, parent, false)

        val name: TextView = view.findViewById(R.id.branchNameTxt)
        val address: TextView = view.findViewById(R.id.branchAddressTxt)

        val item = items[position]
        name.text = item.name
        address.text = item.address

        return view
    }


}