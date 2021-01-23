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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dullabs.notiga.R

@Composable
fun NotificationComponent() {
    Card(Modifier
            .fillMaxWidth()
            .padding(10.dp),elevation = 8.dp) {
        Row(Modifier
                .fillMaxWidth()
                .padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(imageVector = vectorResource(id = R.drawable.ic_whatsapp))
            Spacer(Modifier.width(12.dp))
            Column() {
                Row() {
                    Text("Chrome", style = MaterialTheme.typography.subtitle2)
                    Spacer(Modifier.width(40.dp))
                    Text(text = "+2", style = MaterialTheme.typography.caption)
                }
                Text("We have some.", style = MaterialTheme.typography.overline)
            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Icon(imageVector = vectorResource(id = R.drawable.ic_arrow_right))
            }
        }
    }
}

@Preview
@Composable
fun PreviewNotification() {
    NotificationComponent()
}

