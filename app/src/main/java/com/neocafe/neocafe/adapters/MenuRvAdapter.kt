package com.neocafe.neocafe.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.RvMenuItemBinding
import com.neocafe.neocafe.entities.menu.responses.Menu
import com.neocafe.neocafe.entities.order.Basket

class MenuRvAdapter: RecyclerView.Adapter<MenuRvAdapter.ViewHolder>() {

    private var menuList: List<Menu> = emptyList()
    var clickToDetails: ((Menu)->Unit)? = null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = RvMenuItemBinding.bind(itemView)
        fun bind(menu: Menu){
            Glide.with(binding.image).load(menu.image).into(binding.image)

            binding.nameTxt.text = menu.name
            binding.categoryTxt.text = menu.slug
            binding.priceTxt.text = menu.price
            binding.amountTxt.text = Basket.getAmount(menu).toString()
            if(Basket.getAmount(menu) != 0){
                binding.decrementBtn.visibility = View.VISIBLE
                binding.amountTxt.visibility = View.VISIBLE
            }
            binding.incrementBtn.setOnClickListener {
                binding.decrementBtn.visibility = View.VISIBLE
                binding.amountTxt.visibility = View.VISIBLE
                if(Basket.getAmount(menu) < 9){
                    Basket.addMenu(menu)
                    binding.amountTxt.text = Basket.getAmount(menu).toString()
                }
            }
            binding.decrementBtn.setOnClickListener {
                if(Basket.getAmount(menu) > 0){
                    Basket.delete(menu)
                    binding.amountTxt.text = Basket.getAmount(menu).toString()
                }
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
        val diffUtil = MenuRvDiffUtils(menuList, newList)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        this.menuList = newList
        diffUtilResult.dispatchUpdatesTo(this)
    }

}