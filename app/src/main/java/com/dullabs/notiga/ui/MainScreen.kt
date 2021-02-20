package com.dullabs.notiga.ui

import androidx.annotation.StringRes
import com.dullabs.notiga.R

sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val resourceId: Int,
    val iconId: Int
) {
    object Inbox : BottomNavigationScreens(
        "Inbox",
        R.string.inbox_route,
        R.drawable.ic_inbox_icon
    )

    object BatchTime : BottomNavigationScreens(
        "BatchTime",
        R.string.batch_time_route,
        R.drawable.ic_timer_icon
    )

    object Configure : BottomNavigationScreens(
        "Configure",
        R.string.configure_route,
        R.drawable.ic_app_batching
    )
}

//@Composable
//fun MainScreen() {
//    val navController = rememberNavController()
//    val scaffoldState =
//        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = { NotigaTopAppBar(scaffoldState = scaffoldState) },
//        bottomBar = { NotigaBottomAppBar(navController) },
//        floatingActionButton = { NotigaFloatingActionButton() },
//        floatingActionButtonPosition = FabPosition.Center,
//        isFloatingActionButtonDocked = true
//    ) {
//        MainScreenNavigationConfigurations(navController = navController)
//    }
//}
//
//@Composable
//private fun MainScreenNavigationConfigurations(navController: NavHostController) {
//    NavHost(navController = navController, startDestination = BottomNavigationScreens.Inbox.route) {
//        composable(BottomNavigationScreens.Inbox.route) {
//            InboxScreen()
//        }
//        composable(BottomNavigationScreens.BatchTime.route) {
//            BatchTimeScreen()
//        }
//        composable(BottomNavigationScreens.Configure.route) {
//            AppsConfigureScreen()
//        }
//    }
//}

