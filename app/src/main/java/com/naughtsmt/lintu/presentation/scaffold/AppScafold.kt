package com.naughtsmt.lintu.presentation.scaffold

import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.naughtsmt.lintu.common.Constants.ALL_GAMES_LIST_ID
import com.naughtsmt.lintu.common.Constants.TOP_BAR_JUEGOS
import com.naughtsmt.lintu.navigation.nav_graph.SetupNavGraph
import com.naughtsmt.lintu.presentation.Screens
import com.naughtsmt.lintu.presentation.general_components.ListsDropDownMenu
import com.naughtsmt.lintu.presentation.lists.ListsViewModel
import com.naughtsmt.lintu.presentation.scaffold.components.CustomFab
import com.naughtsmt.lintu.presentation.scaffold.components.CustomTopBar
import com.naughtsmt.lintu.presentation.single_list.SingleListViewModel

@Composable
fun AppScaffold(
    viewModel: SingleListViewModel = hiltViewModel(),
    listsViewModel: ListsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isFocusedCleared = remember { mutableStateOf(true) }
    val currentScreen = remember { mutableStateOf(TOP_BAR_JUEGOS) }

    val floatingActionButton: @Composable () -> Unit = {
        CustomFab(
            showAddGameDropDownMenu = {
                mainViewModel.isDropDownMenuShowed.value = !mainViewModel.isDropDownMenuShowed.value
            })
    }

    val topBar: @Composable () -> Unit = {
        currentDestination?.route?.let {
            CustomTopBar(
                navController = navController,
                toTopGames = { viewModel.getTopGamesList() },
                toAllGames = { viewModel.getGamesFromMainList(ALL_GAMES_LIST_ID) },
                focusCleared = isFocusedCleared,
                currentScreen = currentScreen
            )
        }
    }

    val isInLogin = currentDestination?.route == Screens.LoginScreen.route
    val isInWebView = currentDestination?.route == Screens.WebViewScreen.route
    val isInAuth = currentDestination?.route == Screens.AuthScreen.route + "/{code}"
    val isInGameDetail = currentDestination?.route == Screens.GameDetailScreen.route + "&{gameId}"
    val localFocusManager = LocalFocusManager.current

    Scaffold(

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
        }

    ) { innerPadding ->

        val context = LocalContext.current

        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            SetupNavGraph(
                navController = navController,
                modifier = Modifier
                    .padding(innerPadding)
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            localFocusManager.clearFocus()
                            mainViewModel.isDropDownMenuShowed.value = false
                            isFocusedCleared.value = true
                        })
                    },
                listsViewModel = listsViewModel,
                mainViewModel = mainViewModel,
                isEditTextShown = mainViewModel.isDropDownMenuShowed,
                currentScreen = currentScreen
            )
            if (mainViewModel.isDropDownMenuShowed.value) {

                ListsDropDownMenu(
                    addGameToList = {
                        mainViewModel.addGameToList(
                            mainViewModel.currentSelectedListId.value,
                            mainViewModel.currentGameDetailId.value
                        )
                        mainViewModel.isDropDownMenuShowed.value = false
                        Toast.makeText(context, "Juego añadido", Toast.LENGTH_SHORT).show()
                    },
                    mainViewModel = mainViewModel
                )
            }
        }
    }
}
