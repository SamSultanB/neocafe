package com.neocafe.neocafe.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.RvHisoryOfOrdersItemBinding
import com.neocafe.neocafe.entities.order.responses.Order
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

class LastOrdersRv: RecyclerView.Adapter<LastOrdersRv.ViewHolder>() {

    private var lastOrders: List<Order> = emptyList()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = RvHisoryOfOrdersItemBinding.bind(itemView)
        fun bind(item: Order){
            Glide.with(binding.imageImg).load(item.branch.image).into(binding.imageImg)
            Log.i("test", item.branch.image)
            binding.addressTxt.text = item.branch.name
            binding.dateTxt.text = convertToCustomFormat(item.created)
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
    fun convertToCustomFormat(dateTime: String): String {
        // Define a custom formatter
        val dateTime2 = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))

        // Convert to a custom format
        return dateTime2.atOffset(ZoneOffset.UTC)
            .atZoneSameInstant(ZoneOffset.ofHours(6))  // Adjust to the desired time zone
            .format(formatter)
    }
}