package com.naughtsmt.lintu.navigation.nav_graph

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.naughtsmt.lintu.common.Constants
import com.naughtsmt.lintu.presentation.Screens
import com.naughtsmt.lintu.presentation.authLoading.AuthProcessingScreen
import com.naughtsmt.lintu.presentation.login.LoginScreen
import com.naughtsmt.lintu.presentation.web_view.WebViewScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController, modifier: Modifier) {

    navigation(
        startDestination = Screens.LoginScreen.route,
        route = Constants.AUTH_ROUTE
    ) {

        composable(
            route = Screens.LoginScreen.route
        ) {
            LoginScreen(navController = navController, modifier = modifier)
        }
        composable(
            route = Screens.WebViewScreen.route
        ) {
            WebViewScreen(navController = navController, modifier = modifier)
        }

        composable(
            route = Screens.AuthScreen.route + "/{code}"
        ) {
            AuthProcessingScreen(
                navController = navController,
                modifier = modifier,
            )
        }
    }
}