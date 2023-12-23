package com.neocafe.neocafe.adapters

import androidx.recyclerview.widget.DiffUtil
import com.neocafe.neocafe.entities.menu.responses.Menu

class MenuRvDiffUtils(
    private val oldList: List<Menu>,
    private val newList: List<Menu>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return when{
            oldList[oldItemPosition].name != newList[newItemPosition].name -> false
            oldList[oldItemPosition].image != newList[newItemPosition].image -> false
            oldList[oldItemPosition].available != newList[newItemPosition].available -> false
            oldList[oldItemPosition].extra_product != newList[newItemPosition].extra_product -> false
            oldList[oldItemPosition].category != newList[newItemPosition].category -> false
            oldList[oldItemPosition].slug != newList[newItemPosition].slug -> false
            oldList[oldItemPosition].description != newList[newItemPosition].description -> false
            oldList[oldItemPosition].price != newList[newItemPosition].price -> false
            else -> true
        }

    }

}