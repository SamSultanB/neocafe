package com.neocafe.neocafe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.NotificationItemBinding
import com.neocafe.neocafe.entities.branches.Branche
import com.neocafe.neocafe.utils.Notification

class NotificatioinsRvAdapter: RecyclerView.Adapter<NotificatioinsRvAdapter.ViewHolder>() {

    var notificationsList: MutableList<Notification> = mutableListOf()
//    var clickToDelete: ((Notification)->Unit)? = null
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val binding = NotificationItemBinding.bind(itemView)
        fun bind(notification: Notification){
            binding.statusTxt.text = notification.status
            binding.ordersTxt.text = notification.orders
            binding.timeTxt.text = notification.time
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notificationsList[position])
//        holder.binding.root.setOnClickListener{clickToDelete?.invoke(notificationsList[position])}
    }

    override fun getItemCount(): Int {
        return notificationsList.size
    }

    fun setList(newNotificatioins: List<Notification>){
        val diffUtil = NotificationsRvDiffUtils(notificationsList, newNotificatioins)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        this.notificationsList = newNotificatioins.toMutableList()
        diffUtilResult.dispatchUpdatesTo(this)
    }

    fun delete(position: Int){
        notificationsList.removeAt(position)
        notifyItemRemoved(position)
    }

}