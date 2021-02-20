package com.dullabs.notiga.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import com.dullabs.notiga.ui.BottomNavigationScreens

@Composable
fun NotigaBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavigationScreens>
) {
    BottomNavigation {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.iconId),
                        contentDescription = stringResource(
                            id = screen.resourceId
                        )
                    )
                },
                label = { Text(stringResource(id = screen.resourceId)) },
                selected = currentRoute == screen.route, alwaysShowLabels = false,
                onClick = {
                    // Navigate to the "screen.route” destination only if we’re not already on
                    // the "screen.route" destination, avoiding multiple copies on the top of the
                    // back stack
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
}