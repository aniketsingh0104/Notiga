package com.dullabs.notiga.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dullabs.notiga.R
import com.dullabs.notiga.data.Notification
import com.dullabs.notiga.ui.theme.NotigaTheme

@Composable
fun NotificationComponent(notification: Notification) {
    Card(Modifier
            .fillMaxWidth().padding(5.dp), elevation = 10.dp) {
        Row(Modifier
                .fillMaxWidth()
                .padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = notification.getAppIconId()), contentDescription = "notification app icon")
            Spacer(Modifier.width(12.dp))
            Column {
                Row {
                    Text(notification.getAppName(), style = MaterialTheme.typography.subtitle2)
                    Spacer(Modifier.width(40.dp))
                    Text(text = "+2", color = MaterialTheme.colors.secondaryVariant, style = MaterialTheme.typography.caption)
                }
                Text(notification.getNotificationDescription(), modifier = Modifier.width(150.dp), style = MaterialTheme.typography.overline, overflow = TextOverflow.Ellipsis, maxLines = 1)
            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Icon(painter = painterResource(id = R.drawable.ic_arrow_right), contentDescription = "right arrow")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNotification() {
    val notification = Notification(R.drawable.ic_whatsapp, "Chrome", "We have some crap that you want to check out.")
    NotigaTheme {
        NotificationComponent(notification)
    }
}

