package com.neocafe.neocafe.adapters

import androidx.recyclerview.widget.DiffUtil
import com.neocafe.neocafe.entities.branches.Branche

class FilialsRvDiffUtils(
    private val oldList: List<Branche>,
    private val newList: List<Branche>
): DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].name != newList[newItemPosition].name -> false
            oldList[oldItemPosition].image != newList[newItemPosition].image -> false
            oldList[oldItemPosition].address != newList[newItemPosition].address -> false
            oldList[oldItemPosition].phone_number != newList[newItemPosition].phone_number -> false
            oldList[oldItemPosition].work_schedule != newList[newItemPosition].work_schedule -> false
            else -> true
        }
    }
}