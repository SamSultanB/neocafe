package com.neocafe.neocafe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.RvHisoryOfOrdersItemBinding
import com.neocafe.neocafe.utils.HistoryOfOrdersItem

class LastOrdersRv: RecyclerView.Adapter<LastOrdersRv.ViewHolder>() {

    var lastOrders: List<HistoryOfOrdersItem> = emptyList()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = RvHisoryOfOrdersItemBinding.bind(itemView)
        fun bind(historyOfOrdersItem: HistoryOfOrdersItem){
            binding.addressTxt.text = historyOfOrdersItem.address
            binding.itemsNamesTxt.text = historyOfOrdersItem.orders
            binding.dateTxt.text = historyOfOrdersItem.date
            binding.imageImg.setImageResource(historyOfOrdersItem.image)
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
}