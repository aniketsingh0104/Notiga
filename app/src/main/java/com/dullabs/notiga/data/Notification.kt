package com.dullabs.notiga.data

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

class Notification(
    private val appIcon: Painter,
    private val appName: String,
    private val notificationDescription: String
) {
    fun getAppName(): String {
        return appName
    }

    fun getAppIcon(): Painter {
        return appIcon
    }

    fun getNotificationDescription(): String {
        return notificationDescription
    }
}