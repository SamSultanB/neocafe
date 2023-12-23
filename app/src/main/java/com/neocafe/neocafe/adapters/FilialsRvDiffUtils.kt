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
            oldList[oldItemPosition].monday != newList[newItemPosition].monday -> false
            oldList[oldItemPosition].tuesday != newList[newItemPosition].tuesday -> false
            oldList[oldItemPosition].wednesday != newList[newItemPosition].wednesday -> false
            oldList[oldItemPosition].thursday != newList[newItemPosition].thursday -> false
            oldList[oldItemPosition].friday != newList[newItemPosition].friday -> false
            oldList[oldItemPosition].saturday != newList[newItemPosition].saturday -> false
            oldList[oldItemPosition].sunday != newList[newItemPosition].sunday -> false
            else -> true
        }
    }
}