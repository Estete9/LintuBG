package com.naughtsmt.lintu.presentation

//import com.naughtsmt.lintu.presentation.image_screen.ImageScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.naughtsmt.lintu.navigation.NavigationComponent
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

                    NavigationComponent(/*context = this*/)
                }
            }
        }
    }
}

