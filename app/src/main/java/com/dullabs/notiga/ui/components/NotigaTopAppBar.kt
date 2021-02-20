package com.dullabs.notiga.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dullabs.notiga.R

@Composable
fun NotigaTopAppBar(scaffoldState: ScaffoldState) {
    TopAppBar(
        title = { Text("Inbox") },
        navigationIcon = {
            Icon(painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "drawer icon",
                modifier = Modifier
                    .clickable(
                        onClick = {
//                        scaffoldState.drawerState.open()
                        }
                    )
                    .padding(start = 10.dp))
        },
        actions = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 15.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_share_min),
                    contentDescription = "share"
                )
                Spacer(modifier = Modifier.width(20.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_star_border),
                    contentDescription = "rate us"
                )
                Spacer(modifier = Modifier.width(20.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "delete all notifications"
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTopAppBar() {
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    NotigaTopAppBar(scaffoldState = scaffoldState)
}