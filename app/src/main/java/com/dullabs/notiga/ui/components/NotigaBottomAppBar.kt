package com.dullabs.notiga.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController

@Composable
fun NotigaBottomAppBar(navController: NavHostController) {
    val bottomNavigationItems = listOf(
        com.dullabs.notiga.ui.BottomNavigationScreens.Inbox,
        com.dullabs.notiga.ui.BottomNavigationScreens.BatchTime,
        com.dullabs.notiga.ui.BottomNavigationScreens.Configure
    )
    val fabShape = CircleShape
    BottomAppBar(
        modifier = Modifier.padding(20.dp),
        elevation = 18.dp) {
        NotigaBottomNavigation(navController = navController, items = bottomNavigationItems)
    }
}

