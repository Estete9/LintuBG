package com.naughtsmt.lintu.presentation.scaffold

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.naughtsmt.lintu.common.Constants.ALL_GAMES_LIST_ID
import com.naughtsmt.lintu.presentation.Screens

@Composable
fun Fab(showDropDownMenu: () -> Unit) {
    FloatingActionButton(onClick = { showDropDownMenu() }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "add game floating button")
    }
}

@Composable
fun BottomBar(navController: NavController, screens: List<Screens.NavBarScreens>) {
    BottomAppBar(
//        cutoutShape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50))
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        screens.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = "${screen.title} icon"
                    )
                },
                label = { Text(text = screen.title) },
                selected = currentDestination == screen,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id)
//                        launchSingleTop = true
                    }
                })
        }
    }
}

@Composable
fun TopBar(
    navController: NavController,
    currentScreenRoute: String,
    toTopGames: () -> Unit,
    toAllGames: () -> Unit
) {
    TopAppBar(backgroundColor = Color.Transparent) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = "Tus juegos",
                    color = MaterialTheme.colors.primary,
                    fontWeight = if (currentScreenRoute == Screens.GameListScreen.route + "?&listId=$ALL_GAMES_LIST_ID") {
                        FontWeight.Bold
                    } else {
                        FontWeight.Normal
                    },
                    modifier = Modifier.clickable {
                        toAllGames()
                        navController.navigate(Screens.GameListScreen.route) /*{
                            launchSingleTop = true
                        }*/
                    })

                Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                Divider(
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .height(15.dp)
                        .width(1.dp)
                )

                Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                Text(text = "Top Ranking",
                    color = MaterialTheme.colors.primary,
                    fontWeight = if (currentScreenRoute == Screens.GameListScreen.route) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Normal
                    },
                    modifier = Modifier.clickable {
                        toTopGames()
                        navController.navigate(Screens.GameListScreen.route)
                    })
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.clickable { },
                    imageVector = Icons.Filled.Search,
                    tint = MaterialTheme.colors.secondary,
                    contentDescription = "Search Icon"
                )
            }
        }
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