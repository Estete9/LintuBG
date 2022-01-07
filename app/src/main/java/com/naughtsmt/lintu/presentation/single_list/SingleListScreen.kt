package com.naughtsmt.lintu.presentation.single_list

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naughtsmt.lintu.common.Constants
import com.naughtsmt.lintu.presentation.Screens
import com.naughtsmt.lintu.presentation.scaffold.MainViewModel
import com.naughtsmt.lintu.presentation.single_list.components.GameListItem


val tag = "SingleScreenTag"

@Composable
fun SingleListScreen(
    navController: NavController,
    singleListViewModel: SingleListViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    modifier: Modifier,
    currentScreen: MutableState<String>,
) {
    val singleListState = singleListViewModel.singleListState.value
    val isEditing = remember { mutableStateOf(false) }
    val needsRefresh = remember { mutableStateOf(false) }

    val context = LocalContext.current

    mainViewModel.currentSelectedListId.value = singleListViewModel.currentListId.value
    val rememberedCurrentScreen by rememberUpdatedState(newValue = currentScreen.value)

    LaunchedEffect(key1 = Unit) {
        if (rememberedCurrentScreen == Constants.TOP_BAR_JUEGOS) {
            singleListViewModel.getGamesFromMainList(Constants.ALL_GAMES_LIST_ID)
        }
        if (rememberedCurrentScreen == Constants.TOP_BAR_RANKING) {
            singleListViewModel.getTopGamesList()
        }
        if (needsRefresh.value) {
            singleListViewModel.getGamesFromMainList(rememberedCurrentScreen)
        }
    }
    LaunchedEffect(key1 = mainViewModel.refreshState.value) {
        Log.d(tag, "second effect running")
        if (mainViewModel.refreshState.value) {
            Log.d(tag, "need refresh: ${needsRefresh.value}")
            singleListViewModel.getGamesFromMainList(mainViewModel.currentSelectedListId.value)
            mainViewModel.refreshState.value = false
//            needsRefresh.value = false
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        Column() {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                contentAlignment = CenterEnd
            ) {
                if (currentScreen.value != Constants.TOP_BAR_RANKING) {
                    Text(
                        text = if (!isEditing.value) "Editar" else "Dejar de editar",
                        color = if (!isEditing.value) MaterialTheme.colors.onSurface else Color.Red,
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.clickable { isEditing.value = !isEditing.value }
                    )
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(CenterHorizontally)
                            .padding(horizontal = 14.dp)
                    ) {
                        Text(
                            text = "¿Qué vamos a jugar hoy?",
                            modifier = Modifier.align(Alignment.TopCenter),
                            color = MaterialTheme.colors.primary,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.h3,
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp
                        )
                    }
//                }
                }

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }

                items(singleListState.games) { game ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        GameListItem(
                            game = game,
                            onItemClicked = {
                                navController.navigate(Screens.GameDetailScreen.route + "&${game.id}")
                            },
//                            mainViewModel = mainViewModel,
                            currentScreen = currentScreen,
                            onDeleteClicked = {
                                mainViewModel.deleteGameFromList(
                                    mainViewModel.currentSelectedListId.value,
                                    game.id
                                )
                                Toast.makeText(context, "Juego removido", Toast.LENGTH_SHORT).show()
                            },
                            isEditing = isEditing.value,
                            finishedEditing = { isEditing.value = false },
                            refresh = { needsRefresh.value = true },
                            ShowAddGameDropDownMenu = {
                                mainViewModel.isDropDownMenuShowed.value =
                                    !mainViewModel.isDropDownMenuShowed.value
                            },
                            mainViewModel = mainViewModel
                        )
                        if (game != singleListState.games.last()) {
                            Divider(
                                color = MaterialTheme.colors.secondaryVariant,
                                modifier = Modifier
                                    .height(1.dp)
                                    .fillMaxWidth(0.9f)
                            )
                        }
                    }
                }

            }
        }

        if (singleListState.error.isNotBlank()) {
            Text(
                text = singleListState.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if (singleListState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}