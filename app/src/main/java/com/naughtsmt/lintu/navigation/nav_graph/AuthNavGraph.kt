package com.naughtsmt.lintu.navigation.nav_graph

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.naughtsmt.lintu.common.Constants
import com.naughtsmt.lintu.presentation.Screens
import com.naughtsmt.lintu.presentation.login.LoginScreen
import com.naughtsmt.lintu.presentation.scaffold.MainViewModel
import com.naughtsmt.lintu.presentation.web_view.WebViewScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController, modifier: Modifier/*, mainViewModel: MainViewModel*/) {

    navigation(
        startDestination = Screens.LoginScreen.route,
        route = Constants.AUTH_ROUTE
    ) {

        composable(
            route = Screens.LoginScreen.route
        ) {
            LoginScreen(navController = navController, modifier = modifier/*, mainViewModel = mainViewModel*/)
        }
        composable(
            route = Screens.WebViewScreen.route
        ) {
            WebViewScreen(navController = navController, modifier = modifier/*, mainViewModel = mainViewModel*/)
        }
    }
}