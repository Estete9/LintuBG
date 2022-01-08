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
    listsViewModel: ListsViewModel,
    isEditTextShown: MutableState<Boolean>,
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
            modifier = modifier
        )
        homeNavGraph(
            navController = navController,
            modifier = modifier,
            listsViewModel = listsViewModel,
            mainViewModel = mainViewModel,
            isEditTextShown = isEditTextShown,
            currentScreen = currentScreen
        )
    }
}