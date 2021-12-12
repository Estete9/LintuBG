package com.naughtsmt.lintu.presentation.login

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.naughtsmt.lintu.R
import com.naughtsmt.lintu.common.Constants.URL_TEXT
import com.naughtsmt.lintu.presentation.Screen


@Composable
fun LoginScreen(
    navController: NavController,
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
            navController.navigate(Screen.WebViewScreen.route)
        }) {
            Text(text = "Conectar con Board Game Atlas")
        }
    }
}
