package com.naughtsmt.lintu.presentation.login

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.naughtsmt.lintu.R
import com.naughtsmt.lintu.common.Constants
import com.naughtsmt.lintu.common.Constants.URL_TEXT
import com.naughtsmt.lintu.domain.use_case.login.LoginUseCase
import com.naughtsmt.lintu.presentation.Screen

@Composable
fun LoginScreen(
//    context: Context,
    navController: NavController
) {



    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.lintu_logo),
            contentDescription = "Logo Lintu",
            modifier = Modifier
                .height(250.dp)
                .width(250.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )
        Button(onClick = {
//            LoginUseCase(navController = navController)
            navController.navigate(Screen.WebViewScreen.route + "/$URL_TEXT")
        }) {
            Text(text = "Conectar con Board Game Atlas")
        }
    }
}

