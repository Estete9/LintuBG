package com.naughtsmt.lintu.presentation.scaffold

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.naughtsmt.lintu.common.Constants.ALL_GAMES_LIST_ID
import com.naughtsmt.lintu.navigation.nav_graph.SetupNavGraph
import com.naughtsmt.lintu.presentation.Screens
import com.naughtsmt.lintu.presentation.game_list.GameListViewModel
import com.naughtsmt.lintu.presentation.general_components.DropDownMenu
import com.naughtsmt.lintu.presentation.lists.ListsViewModel
import com.naughtsmt.lintu.presentation.screensFromBottomNav

//val tag = "APP_SCAFFOLD"

@Composable
fun AppScaffold(
    viewModel: GameListViewModel = hiltViewModel(),
    listsViewModel: ListsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val isDropDownMenuShowed = remember { mutableStateOf(false) }
    val isFocusedCleared = remember { mutableStateOf(true) }

    val floatingActionButton: @Composable () -> Unit = {
        Fab(
            showDropDownMenu = { isDropDownMenuShowed.value = !isDropDownMenuShowed.value })
    }
    val bottomBar: @Composable () -> Unit = {
        BottomBar(navController = navController, screens = screensFromBottomNav)
    }
    val topBar: @Composable () -> Unit = {
        currentDestination?.route?.let {
            TopBar(
                navController = navController,
                currentScreenRoute = it,
                toTopGames = { viewModel.getTopGamesList() },
                toAllGames = { viewModel.getGamesFromMainList(ALL_GAMES_LIST_ID) },
                viewModel = mainViewModel,
                focusCleared = isFocusedCleared
            )
        }
    }

    val isInLogin = currentDestination?.route == Screens.LoginScreen.route
    val isInWebView = currentDestination?.route == Screens.WebViewScreen.route
    val isInAuth = currentDestination?.route == Screens.AuthScreen.route + "/{code}"
    val isInGameDetail = currentDestination?.route == Screens.GameDetailScreen.route + "&{gameId}"
    val localFocusManager = LocalFocusManager.current
    Scaffold(

        bottomBar = {
            if (!isInLogin) {
                if (!isInWebView) {
                    if (!isInAuth) {
                        bottomBar()
                    }
                }
            }
        },
        topBar = {
            if (!isInLogin) {
                if (!isInWebView) {
                    if (!isInAuth) {
                        topBar()
                    }
                }
            }
        },
        floatingActionButton = {
            if (isInGameDetail) {
                floatingActionButton()
            }
        },
//        isFloatingActionButtonDocked = isInGameDetail,
//        floatingActionButtonPosition = FabPosition.Center

    ) { innerPadding ->
        SetupNavGraph(
            navController = navController,
            modifier = Modifier
                .padding(innerPadding)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        localFocusManager.clearFocus()
                        isFocusedCleared.value = true
                    })
                },
            viewModel = viewModel,
            listsViewModel = listsViewModel,
            mainViewModel = mainViewModel
        )
        if (isDropDownMenuShowed.value) {
            DropDownMenu(
                addGameToList = {
                    require(
                        mainViewModel.currentListId.value.isNotBlank() &&
                                mainViewModel.currentGameId.value.isNotBlank()
                    ) {
                        "list and game must be entered"
                    }
                    mainViewModel.addGameToList(
                        mainViewModel.currentListId.value,
                        mainViewModel.currentGameId.value
                    )
                    isDropDownMenuShowed.value = false
                },
                mainViewModel = mainViewModel
            )
        }
    }
}
