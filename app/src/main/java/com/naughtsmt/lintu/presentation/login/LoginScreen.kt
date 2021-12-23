package com.naughtsmt.lintu.presentation.login

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
import com.naughtsmt.lintu.presentation.Screens


@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier,
//    mainViewModel: MainViewModel
) {
//    mainViewModel.setCurrentScreen(Screens.LoginScreen)

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
            navController.navigate(Screens.WebViewScreen.route) {
                navController.popBackStack()
            }
        }) {
            Text(text = "Conectar con Board Game Atlas")
        }
    }
}
