package com.neocafe.neocafe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.NotificationItemBinding
import com.neocafe.neocafe.utils.Notification

class NotificatioinsRvAdapter: RecyclerView.Adapter<NotificatioinsRvAdapter.ViewHolder>() {

    private var notificationsList: List<Notification> = emptyList()
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
    }

    override fun getItemCount(): Int {
        return notificationsList.size
    }

    fun setList(newNotificatioins: List<Notification>){
        notificationsList = newNotificatioins
    }
}