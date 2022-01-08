package com.naughtsmt.lintu.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.naughtsmt.lintu.R
import com.naughtsmt.lintu.presentation.Screens


@Composable
fun LoginScreen(
    navController: NavController,
) {
    Column() {

        Spacer(modifier = Modifier.fillMaxHeight(0.4f))
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_lintu_logo_light_horiz),
                    contentDescription = "Logo Lintu", modifier = Modifier.fillMaxWidth(0.42f)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                )
                Text(
                    text = "Conecta con la librería más grande de juegos de mesa",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(2 / 3f)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                )
                Button(
                    onClick = {
                        navController.navigate(Screens.WebViewScreen.route) {
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.52f)
                        .padding(8.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
                ) {
                    Text(
                        text = "CONECTAR",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.button,
                        color = MaterialTheme.colors.onSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.fillMaxHeight(1 / 6f))
            Text(
                text = "Database powered by Board Game Atlas", fontSize = 12.sp,
                style = MaterialTheme.typography.caption
            )
            Spacer(modifier = Modifier.fillMaxHeight(1 / 8f))
        }
    }
}
