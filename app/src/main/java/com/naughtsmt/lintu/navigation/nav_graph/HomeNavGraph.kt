package com.naughtsmt.lintu.navigation.nav_graph

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.naughtsmt.lintu.common.Constants
import com.naughtsmt.lintu.presentation.Screens
import com.naughtsmt.lintu.presentation.game_detail.GameDetailScreen
import com.naughtsmt.lintu.presentation.game_list.GameListScreen
import com.naughtsmt.lintu.presentation.lists.ListsScreen
import com.naughtsmt.lintu.presentation.profile.ProfileScreen
import com.naughtsmt.lintu.presentation.single_list.SingleListScreen

fun NavGraphBuilder.homeNavGraph(navController: NavHostController, modifier: Modifier) {
    navigation(
        startDestination = Screens.ListsScreen.route,
        route = Constants.HOME_ROUTE
    ) {

        composable(
            route = Screens.ListsScreen.route + "&{code}"
        ) {
            ListsScreen(navController = navController, modifier = modifier)
        }
        composable(
            route = Screens.GameListScreen.route
        ) {
            GameListScreen(navController, modifier = modifier)
        }

        composable(
            route = Screens.GameDetailScreen.route + "&{gameId}"
        ) {
            GameDetailScreen(navController, modifier = modifier)
        }
        composable(
            route = Screens.SingleListScreen.route + "&{singleListId}"
        ) {
            SingleListScreen(navController, modifier = modifier)
        }
        composable(route = Screens.HomeScreens.ProfileScreen.route) {
            ProfileScreen()
        }
    }

}