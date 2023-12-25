package com.neocafe.neocafe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.RvHisoryOfOrdersItemBinding
import com.neocafe.neocafe.entities.order.requests.Order

class LastOrdersRv: RecyclerView.Adapter<LastOrdersRv.ViewHolder>() {

    private var lastOrders: List<Order> = emptyList()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = RvHisoryOfOrdersItemBinding.bind(itemView)
        fun bind(item: Order){
            Glide.with(binding.imageImg).load(item.branch.image).into(binding.imageImg)
            binding.addressTxt.text = item.branch.name
            binding.dateTxt.text = item.created
            binding.itemsNamesTxt.text = item.items[0].menu.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastOrdersRv.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_hisory_of_orders_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LastOrdersRv.ViewHolder, position: Int) {
        holder.bind(lastOrders[position])
    }

    override fun getItemCount(): Int {
        return lastOrders.size
    }

    fun setItems(newList: List<Order>){
        lastOrders = newList
    }
}