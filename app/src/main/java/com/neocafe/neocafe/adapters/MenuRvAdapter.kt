package com.neocafe.neocafe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.RvMenuItemBinding
import com.neocafe.neocafe.utils.Test

class MenuRvAdapter: RecyclerView.Adapter<MenuRvAdapter.ViewHolder>() {

    private var menuList: List<Test> = emptyList()
    var clickToDetails: ((Test)->Unit)? = null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = RvMenuItemBinding.bind(itemView)
        fun bind(test: Test){
            Glide.with(binding.image).load(test.image).into(binding.image)
            binding.nameTxt.text = test.name
            binding.categoryTxt.text = test.category
            binding.priceTxt.text = test.price.toString()
            binding.incrementBtn.setOnClickListener {
                binding.decrementBtn.visibility = View.VISIBLE
                binding.amountTxt.visibility = View.VISIBLE
                if(test.amount < 9){
                    test.amount++
                }
                binding.amountTxt.text = test.amount.toString()
            }
            binding.decrementBtn.setOnClickListener {
                if(test.amount > 0){
                    test.amount--;
                }
                binding.amountTxt.text = test.amount.toString()
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


    fun setMenuList(newList: List<Test>){
        menuList = newList
    }

}