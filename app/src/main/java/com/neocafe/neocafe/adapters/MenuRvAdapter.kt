package com.neocafe.neocafe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.RvMenuItemBinding
import com.neocafe.neocafe.utils.Basket
import com.neocafe.neocafe.utils.Menu

class MenuRvAdapter: RecyclerView.Adapter<MenuRvAdapter.ViewHolder>() {

    private var menuList: List<Menu> = emptyList()
    var clickToDetails: ((Menu)->Unit)? = null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = RvMenuItemBinding.bind(itemView)
        fun bind(menu: Menu){
            Glide.with(binding.image).load(menu.image).into(binding.image)
            binding.nameTxt.text = menu.name
            binding.categoryTxt.text = menu.category
            binding.priceTxt.text = menu.price.toString()
            binding.amountTxt.text = menu.amount.toString()
            if(menu.amount != 0){
                binding.amountTxt.visibility = View.VISIBLE
                binding.decrementBtn.visibility = View.VISIBLE
            }
            binding.incrementBtn.setOnClickListener {
                if(menu.amount == 1){
                    Basket.basket += menu
                }
                binding.decrementBtn.visibility = View.VISIBLE
                binding.amountTxt.visibility = View.VISIBLE
                if(menu.amount < 9){
                    menu.amount++
                }
                binding.amountTxt.text = menu.amount.toString()
            }
            binding.decrementBtn.setOnClickListener {
                if(menu.amount > 0){
                    menu.amount--;
                }
                binding.amountTxt.text = menu.amount.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(menuList[position])
        holder.binding.nameTxt.setOnClickListener { clickToDetails?.invoke(menuList[position]) }
    }

    override fun getItemCount(): Int {
        return menuList.size
    }


    fun setMenuList(newList: List<Menu>){
        menuList = newList
    }

}