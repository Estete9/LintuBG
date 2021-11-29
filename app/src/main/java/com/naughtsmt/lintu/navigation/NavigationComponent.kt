package com.naughtsmt.lintu.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.naughtsmt.lintu.common.Constants
import com.naughtsmt.lintu.presentation.Screen
import com.naughtsmt.lintu.presentation.game_detail.GameDetailScreen
import com.naughtsmt.lintu.presentation.game_list.GameListScreen
import com.naughtsmt.lintu.presentation.login.LoginScreen
import com.naughtsmt.lintu.presentation.web_view.WebViewScreen

@Composable
fun NavigationComponent(/*context: Context*/) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.GameListScreen.route
//                        startDestination = Screen.SplashScreen.route
    ) {
//                        composable(
//                            route = Screen.SplashScreen.route
//                        ) {
//                            SplashScreen(navController)
//                        }
//                        composable(
//                            route = Screen.ImageScreen.route
//                        ) {
//                            ImageScreen()
//                        }
        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(/*context,*/ navController)
        }
        composable(
            route = Screen.WebViewScreen.route + "/${Constants.URL_TEXT}"

        ) {

            WebViewScreen()


        }
        composable(
            route = Screen.GameListScreen.route
        ) {
            GameListScreen(navController/*, context = applicationContext*/)
        }

        composable(
            route = Screen.GameDetailScreen.route + "{game_id}"
        ) {
            GameDetailScreen(navController)
        }
    }
}