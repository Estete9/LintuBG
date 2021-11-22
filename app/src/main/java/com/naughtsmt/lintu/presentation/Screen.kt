package com.naughtsmt.lintu.presentation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object GameListScreen : Screen("game_list_screen")
    object GameDetailScreen : Screen("game_detail_screen")
    object ImageScreen: Screen("image_screen")
}
