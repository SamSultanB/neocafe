package com.neocafe.neocafe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.RvHisoryOfOrdersItemBinding

class LastOrdersRv: RecyclerView.Adapter<LastOrdersRv.ViewHolder>() {

    var lastOrders: List<String> = emptyList()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = RvHisoryOfOrdersItemBinding.bind(itemView)
        fun bind(){
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastOrdersRv.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_hisory_of_orders_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LastOrdersRv.ViewHolder, position: Int) {
//        holder.bind(lastOrders[position])
    }

    override fun getItemCount(): Int {
        return lastOrders.size
    }
}