package com.naughtsmt.lintu.presentation.splash_screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.naughtsmt.lintu.R
import com.naughtsmt.lintu.presentation.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.6f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f)
                        .getInterpolation(it)
                })
        )
        delay(2000L)
        navController.navigate(Screens.GameListScreen.route)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.lintu_logo),
            contentDescription = "logo de Lintu",
            modifier = Modifier.scale(scale.value)
        )
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun SplashScreenPreview() {
//    val navController = rememberNavController()
//    SplashScreen(navController = navController)
//}