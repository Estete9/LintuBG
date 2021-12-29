package com.naughtsmt.lintu.presentation.single_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naughtsmt.lintu.R
import com.naughtsmt.lintu.common.Constants
import com.naughtsmt.lintu.presentation.Screens
import com.naughtsmt.lintu.presentation.game_list.GameListViewModel
import com.naughtsmt.lintu.presentation.game_list.components.GameListItem
import com.naughtsmt.lintu.presentation.scaffold.MainViewModel


@Composable
fun SingleListScreen(
    navController: NavController,
    singleListViewModel: SingleListViewModel = hiltViewModel(),
    topBarViewModel: GameListViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    modifier: Modifier,
    currentScreen: MutableState<String>,
) {
    val singleListState = singleListViewModel.state.value
    val games = remember { singleListState.games.map { it } }

    LaunchedEffect(key1 = Unit) {
        if (currentScreen.value == Constants.TOP_BAR_JUEGOS) {
            singleListViewModel.getGamesFromMainList(Constants.ALL_GAMES_LIST_ID)
        }
        if (currentScreen.value == Constants.TOP_BAR_RANKING) {
            singleListViewModel.getTopGamesList()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primaryVariant)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lintu_logo_header),
                        contentDescription = "game list header",
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.TopCenter),
                        contentScale = ContentScale.FillWidth
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                    ) {
                        Text(
                            text = "¿Qué vamos a jugar hoy?",
                            modifier = Modifier.align(Alignment.TopCenter),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.h3,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            items(singleListState.games) { game ->
                GameListItem(
                    game = game,
                    onItemClicked = {
                        navController.navigate(Screens.GameDetailScreen.route + "&${game.id}")
                    },
                    onDeleteClicked = { })

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