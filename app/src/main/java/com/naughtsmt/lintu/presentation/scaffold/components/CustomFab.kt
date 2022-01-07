package com.naughtsmt.lintu.presentation.scaffold.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*

val tag = "COMPONENT"

@Composable
fun CustomFab(showAddGameDropDownMenu: () -> Unit) {
    FloatingActionButton(onClick = { showAddGameDropDownMenu() }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "add game floating button")
    }
}



//@Composable
//fun TopBar(title: String = "", buttonIcon: ImageVector, onButtonClicked: () -> Unit) {
//    TopAppBar(
//        title = {
//            Text(
//                text = title
//            )
//        },
//        navigationIcon = {
//            IconButton(onClick = { onButtonClicked() } ) {
//                Icon(buttonIcon, contentDescription = "")
//            }
//        },
//        backgroundColor = MaterialTheme.colors.primaryVariant
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun TopBarPreview() {
//    val navController = rememberNavController()
//    val previewCurrentScreen = Screens.GameListScreen
//    TopBar(navController = navController, currentScreenRoute = previewCurrentScreen.route)
//}