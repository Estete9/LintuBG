package com.naughtsmt.lintu.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String) {

    //    object SplashScreen : Screen("splash_screen")
    object GameListScreen : Screens("game_list_screen")
    object GameDetailScreen : Screens("game_detail_screen")
    object ImageScreen : Screens("image_screen")
    object LoginScreen : Screens("login_screen")
    object WebViewScreen : Screens("web_view")
    object ListsScreen : Screens("list_of_lists")
    object SingleListScreen : Screens("single_list_screen")

    sealed class NavBarScreens(
        route: String, val title: String, val icon: ImageVector
    ) : Screens(route) {
        object RandomGameListScreen :
            NavBarScreens("random_game_list_screen", "Random", Icons.Filled.Shuffle)
        object ProfileScreen : NavBarScreens("profile_screen", "Perfil", Icons.Filled.Person)
    }
}

val screensFromBottomNav = listOf(
//    Screens.HomeScreens.RandomGameListScreen,
    Screens.NavBarScreens.ProfileScreen
)