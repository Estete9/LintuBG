package com.naughtsmt.lintu.presentation.authLoading

import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naughtsmt.lintu.R
import com.naughtsmt.lintu.presentation.Screens
import kotlinx.coroutines.delay

val tag = "AuthScreen"

@Composable
fun AuthProcessingScreen(
    navController: NavController,
    modifier: Modifier,
    viewModel: AuthPostViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val scale = remember {
        Animatable(0f)
    }
    val textScale = remember {
        Animatable(0f)
    }
//    Log.d(tag, "the auth token issss: ${state.accessToken}")

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.4f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(3f)
                        .getInterpolation(it)
                })
        )
//        delay(700L)
        navController.navigate(Screens.GameListScreen.route) {
            navController.popBackStack()
//            launchSingleTop = true
        }

    }

    LaunchedEffect(key1 = true) {
        textScale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(3f)
                        .getInterpolation(it)
                })
        )

    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (!state.isLoading && state.error.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_success_icon),
                    contentDescription = "login success icon",
                    modifier = Modifier
                        .scale(scale.value),
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                )
                Text(
                    text = "Bienvenido",
                    fontSize = 30.sp,
                    modifier = Modifier.scale(textScale.value)
                )
            }

        }
        if (state.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                CircularProgressIndicator()
            }
        }
        if (state.error.isNotBlank()) {
            Log.d(tag, "auth state error in error checking: $state")
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
        }
    }
}

