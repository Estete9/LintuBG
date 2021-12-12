package com.naughtsmt.lintu.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.naughtsmt.lintu.presentation.Screen
import com.naughtsmt.lintu.presentation.game_detail.GameDetailScreen
import com.naughtsmt.lintu.presentation.game_list.GameListScreen
import com.naughtsmt.lintu.presentation.lists.ListsScreen
import com.naughtsmt.lintu.presentation.login.LoginScreen
import com.naughtsmt.lintu.presentation.single_list.SingleListScreen
import com.naughtsmt.lintu.presentation.web_view.WebViewScreen

var boolean = true
/*   if want to use a bouncy splash screen (with animations)

                        startDestination = Screen.SplashScreen.route
                        composable(
                            route = Screen.SplashScreen.route
                        ) {
                            SplashScreen(navController)
                        }
                        composable(
                            route = Screen.ImageScreen.route
                        ) {
                            ImageScreen()
                        }

                        */


@Composable
fun NavigationHost(
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {

        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.WebViewScreen.route

        ) {
            WebViewScreen(navController = navController)
        }
        composable(
            route = Screen.ListsScreen.route + "&{code}"
        ) {
            ListsScreen(navController = navController)
        }
        composable(
            route = Screen.GameListScreen.route
        ) {
            GameListScreen(navController)
        }

        composable(
            route = Screen.GameDetailScreen.route + "&{gameId}"
        ) {
            GameDetailScreen(navController)
        }
        composable(
            route = Screen.SingleListScreen.route + "&{listId}"
        ) {
            SingleListScreen(navController)
        }
    }
}