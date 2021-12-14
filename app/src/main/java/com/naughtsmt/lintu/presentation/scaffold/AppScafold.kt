package com.naughtsmt.lintu.presentation.scaffold

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.naughtsmt.lintu.navigation.nav_graph.SetupNavGraph
import com.naughtsmt.lintu.presentation.screensFromBottomNav

@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    val bottomBar: @Composable () -> Unit = {
        BottomBar(navController = navController, screens = screensFromBottomNav)
    }
    val topBar: @Composable () -> Unit = {
        TopBar(navController = navController)
    }


    Scaffold(
        bottomBar = { bottomBar() },
        topBar = { topBar() }
    ) { innerPadding ->
        SetupNavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}