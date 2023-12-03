package com.neocafe.neocafe.adapters

import androidx.recyclerview.widget.DiffUtil
import com.neocafe.neocafe.utils.Notification

class NotificationsRvDiffUtils(
    private val oldList: List<Notification>,
    private val newList: List<Notification>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].time == newList[newItemPosition].time
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].orders != newList[newItemPosition].orders -> false
            oldList[oldItemPosition].time != newList[newItemPosition].time -> false
            oldList[oldItemPosition].status != newList[newItemPosition].status -> false
            else -> true
        }
    }
}