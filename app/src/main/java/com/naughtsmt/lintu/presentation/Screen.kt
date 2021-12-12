package com.naughtsmt.lintu.presentation

sealed class Screen(val route: String) {
//    object SplashScreen : Screen("splash_screen")
    object GameListScreen : Screen("game_list_screen")
    object GameDetailScreen : Screen("game_detail_screen")
    object ImageScreen: Screen("image_screen")
    object LoginScreen: Screen("login_screen")
    object WebViewScreen: Screen("web_view")
    object ListsScreen: Screen("list_of_lists")
    object SingleListScreen: Screen("single_list_screen")
}
