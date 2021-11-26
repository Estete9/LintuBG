package com.naughtsmt.lintu.presentation

//import com.naughtsmt.lintu.presentation.image_screen.ImageScreen
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.naughtsmt.lintu.presentation.game_detail.GameDetailScreen
import com.naughtsmt.lintu.presentation.game_list.GameListScreen
import com.naughtsmt.lintu.presentation.ui.theme.LintuTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LintuTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

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
                            route = Screen.GameListScreen.route
                        ) {
                            GameListScreen(navController/*, context = applicationContext*/)
                        }

                        composable(
                            route = Screen.GameDetailScreen.route + "&{gameId}"
                        ) {
                            GameDetailScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
