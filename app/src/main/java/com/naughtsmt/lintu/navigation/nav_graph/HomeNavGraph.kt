package com.naughtsmt.lintu.navigation.nav_graph

import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.naughtsmt.lintu.common.Constants
import com.naughtsmt.lintu.presentation.Screens
import com.naughtsmt.lintu.presentation.game_detail.GameDetailScreen
//import com.naughtsmt.lintu.presentation.game_list.GameListScreen
import com.naughtsmt.lintu.presentation.lists.ListsScreen
import com.naughtsmt.lintu.presentation.lists.ListsViewModel
import com.naughtsmt.lintu.presentation.profile.ProfileScreen
import com.naughtsmt.lintu.presentation.scaffold.MainViewModel
import com.naughtsmt.lintu.presentation.single_list.SingleListScreen
import com.naughtsmt.lintu.presentation.single_list.SingleListViewModel

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: SingleListViewModel,
    listsViewModel: ListsViewModel,
    mainViewModel: MainViewModel,
    currentScreen: MutableState<String>
) {
    navigation(
        startDestination = Screens.GameListScreen.route,
        route = Constants.HOME_ROUTE
    ) {

        composable(
            route = Screens.NavBarScreens.ListsScreen.route
        ) {
            ListsScreen(
                navController = navController,
                modifier = modifier,
                listsViewModel = listsViewModel
//                mainViewModel = mainViewModel
            )
        }
//        composable(
//            route = Screens.GameListScreen.route + "?&listId={listId}",
//            arguments = listOf(navArgument("listId") { defaultValue = ALL_GAMES_LIST_ID })
//        ) { /*backStackEntry ->*/
//            GameListScreen(
//                navController = navController,
//                modifier = modifier,
//                currentScreen = currentScreen,
//                viewModel = viewModel,
//                mainViewModel = mainViewModel
//            )
//        }
        composable(
            route = Screens.SingleListScreen.route + "?&singleListId={singleListId}&name={name}",
            arguments = listOf(navArgument("singleListId") {
                nullable = true
                defaultValue = null
                type = NavType.StringType
            },
                navArgument("name") {
                    nullable = true
                    defaultValue = null
                    type = NavType.StringType
                })
        ) {
            SingleListScreen(
                navController,
                modifier = modifier,
                mainViewModel = mainViewModel,
                currentScreen = currentScreen
//                viewModel = viewModel/*, */
            )
        }

        composable(
            route = Screens.GameDetailScreen.route + "&{gameId}"
        ) {
            GameDetailScreen(navController, modifier = modifier, mainViewModel = mainViewModel)
        }
        composable(route = Screens.NavBarScreens.ProfileScreen.route) {
            ProfileScreen(modifier = modifier/*mainViewModel = mainViewModel*/)
        }
    }

}