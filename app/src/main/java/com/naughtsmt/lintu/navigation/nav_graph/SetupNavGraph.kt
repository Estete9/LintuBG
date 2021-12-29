package com.naughtsmt.lintu.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.naughtsmt.lintu.common.Constants.AUTH_ROUTE
import com.naughtsmt.lintu.common.Constants.ROOT_ROUTE
import com.naughtsmt.lintu.presentation.lists.ListsViewModel
import com.naughtsmt.lintu.presentation.scaffold.MainViewModel
import com.naughtsmt.lintu.presentation.single_list.SingleListViewModel

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
fun SetupNavGraph(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: SingleListViewModel,
    listsViewModel: ListsViewModel,
    mainViewModel: MainViewModel,
    currentScreen: MutableState<String>
) {

    NavHost(
        navController = navController,
        startDestination = AUTH_ROUTE,
        route = ROOT_ROUTE
    ) {
        authNavGraph(
            navController = navController,
            modifier = modifier/*, mainViewModel = mainViewModel*/
        )
        homeNavGraph(
            navController = navController,
            modifier = modifier,
            viewModel = viewModel,
            listsViewModel = listsViewModel,
            mainViewModel = mainViewModel,
            currentScreen = currentScreen
        )
    }
}