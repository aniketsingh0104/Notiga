package com.dullabs.notiga.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.recyclerview.widget.RecyclerView
import com.dullabs.notiga.R
import com.dullabs.notiga.data.Notification
import com.dullabs.notiga.ui.components.NotificationComponent

class NotificationAdapter(
    private var mNotificationsData: ArrayList<Notification>,
    private var mContext: Context
) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mNotificationItem = itemView.findViewById<ComposeView>(R.id.notificationItem)

        fun bindTo(currentNotification: Notification) {
            mNotificationItem.setContent {
                NotificationComponent(currentNotification)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.notification_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(currentNotification = mNotificationsData.get(position))
    }

    override fun getItemCount(): Int {
        return mNotificationsData.size
    }

    fun addItem(notificationItem: Notification) {
        mNotificationsData.add(notificationItem)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        mNotificationsData.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(notificationItem: Notification, position: Int) {
        mNotificationsData.add(position, notificationItem)
        notifyItemInserted(position)
    }

    fun getData(): ArrayList<Notification> {
        return mNotificationsData
    }

    fun getItemAtPosition(position: Int): Notification {
        return mNotificationsData.get(position)
    }
}