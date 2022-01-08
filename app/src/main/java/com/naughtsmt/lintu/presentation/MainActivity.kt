package com.naughtsmt.lintu.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Brush
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.naughtsmt.lintu.presentation.scaffold.AppScaffold
import com.naughtsmt.lintu.presentation.ui.theme.LintuTheme
import com.naughtsmt.lintu.presentation.ui.theme.Typography
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {

            LintuTheme() {
                MaterialTheme(typography = Typography) {

                    Surface(color = MaterialTheme.colors.background) {
                        AppScaffold()
                    }
                }
            }
        }
    }
}



