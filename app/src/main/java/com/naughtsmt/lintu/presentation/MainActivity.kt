package com.naughtsmt.lintu.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.naughtsmt.lintu.presentation.scaffold.AppScaffold
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
//                    val navController = rememberNavController()
                    AppScaffold()
//                    SetupNavGraph(navController = navController, modifier = Modifier)

                }
            }
        }
    }
}



