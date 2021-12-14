package com.naughtsmt.lintu.presentation.scaffold

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.naughtsmt.lintu.navigation.nav_graph.SetupNavGraph
import com.naughtsmt.lintu.presentation.Screens
import com.naughtsmt.lintu.presentation.screensFromBottomNav

@Composable
fun AppScaffold(
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
//    val currentScreenRoute = viewModel.state.value.currentScreens
//    val state = viewModel.state.value

//    val bottomBar: @Composable () -> Unit =
//        if (state.currentScreen != Screens.LoginScreen || state.currentScreen != Screens.WebViewScreen) {
//            { BottomBar(navController = navController, screens = screensFromBottomNav) }
//        } else {
//            {}
//        }

    var bottomBar: @Composable () -> Unit = {
        BottomBar(navController = navController, screens = screensFromBottomNav)
    }
//    val topBar: @Composable () -> Unit =
//        if (state.currentScreen != Screens.LoginScreen || state.currentScreen != Screens.WebViewScreen) {
//            { TopBar(navController = navController, currentScreen = state.currentScreen) }
//        } else {
//            {}
//        }
    val topBar: @Composable () -> Unit = {
        currentDestination?.route?.let {
            TopBar(
                navController = navController,
                currentScreenRoute = it/*state.currentScreen*/
            )
        }
    }

    Scaffold(
//if (currentScreenRoute != Screens.LoginScreen.route) {
//
//}

        bottomBar = {
            if (currentDestination?.route != Screens.LoginScreen.route ) {
                if(currentDestination?.route != Screens.WebViewScreen.route)
                bottomBar()
            }
        },
        topBar = {
            if (currentDestination?.route != Screens.LoginScreen.route) {
                if(currentDestination?.route != Screens.WebViewScreen.route)
                topBar()
            }
        }

//                bottomBar =
//        if (state.currentScreen.route != Screens.LoginScreen.route || state.currentScreen.route != Screens.WebViewScreen.route) {
//            { bottomBar() }
//        } else {
//            {}
//        },
//        topBar = {
//            if (state.currentScreen != Screens.LoginScreen || state.currentScreen != Screens.WebViewScreen) {
//                topBar()
//            }
//        }
    ) { innerPadding ->

        SetupNavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}
