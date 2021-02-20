package com.dullabs.notiga.data

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

class Notification(
    private val appIconId: Int,
    private val appName: String,
    private val notificationDescription: String
) {
    fun getAppName(): String {
        return appName
    }

    fun getAppIconId(): Int {
        return appIconId
    }

    fun getNotificationDescription(): String {
        return notificationDescription
    }
}