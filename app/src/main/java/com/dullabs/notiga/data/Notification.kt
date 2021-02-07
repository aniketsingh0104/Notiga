package com.dullabs.notiga.data

import androidx.compose.ui.graphics.vector.ImageVector

class Notification(
    private val appIcon: ImageVector,
    private val appName: String,
    private val notificationDescription: String
) {
    fun getAppName(): String {
        return appName
    }

    fun getAppIcon(): ImageVector {
        return appIcon
    }

    fun getNotificationDescription(): String {
        return notificationDescription
    }
}