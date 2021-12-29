package com.naughtsmt.lintu.presentation.scaffold.components

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.naughtsmt.lintu.presentation.Screens

@Composable
fun BottomBar(
    navController: NavController,
    screens: List<Screens.NavBarScreens>,
    isInTopBarScreen: MutableState<String>
) {
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
                    isInTopBarScreen.value = ""
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id)
//                        launchSingleTop = true
                    }
                })
        }
    }
}
