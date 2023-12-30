package com.neocafe.neocafe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.RvMenuItemBinding
import com.neocafe.neocafe.entities.order.requests.MTO

class LastOrdersDetailsRv: RecyclerView.Adapter<LastOrdersDetailsRv.ViewHolder>() {

    private var lastOrders: List<MTO> = emptyList()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = RvMenuItemBinding.bind(itemView)
        fun bind(mto: MTO){
            Glide.with(binding.image).load(mto.menu_detail.image).into(binding.image)
            binding.nameTxt.text = mto.menu_detail.name
            binding.categoryTxt.text = mto.menu_detail.description
            binding.priceTxt.text = mto.menu_detail.price
            binding.amountTxt.text = mto.menu_quantity.toString()
            binding.decrementBtn.isVisible = true
            binding.amountTxt.isVisible = true
            binding.incrementBtn.setOnClickListener {
                if(mto.menu_quantity < 9){
                    mto.menu_quantity++
                }
            }
            binding.decrementBtn.setOnClickListener {
                if(mto.menu_quantity > 0){
                    mto.menu_quantity--
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LastOrdersDetailsRv.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LastOrdersDetailsRv.ViewHolder, position: Int) {
        holder.bind(lastOrders[position])
    }

    override fun getItemCount(): Int {
        return lastOrders.size
    }

    fun setItems(newList: List<MTO>){
        lastOrders = newList
    }

}