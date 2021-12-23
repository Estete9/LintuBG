package com.naughtsmt.lintu.presentation.game_list

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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.naughtsmt.lintu.R
import com.naughtsmt.lintu.common.Constants.ALL_GAMES_LIST_ID
import com.naughtsmt.lintu.presentation.Screens
import com.naughtsmt.lintu.presentation.game_list.components.GameListItem


@Composable
fun GameListScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: GameListViewModel,
//    mainViewModel: MainViewModel,
) {
    val state = viewModel.state.value
    val rememberedState = rememberUpdatedState(newValue = state)

    LaunchedEffect(key1 = rememberedState) {
        viewModel.getGamesFromMainList(ALL_GAMES_LIST_ID)
    }
//mainViewModel.setCurrentScreen(Screens.GameListScreen)
    Box(
        modifier = Modifier
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

            items(state.games) { game ->
                GameListItem(
                    game = game,
                    onItemClicked = {
                        navController.navigate(Screens.GameDetailScreen.route + "&${game.id}")
                    }
                )

            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}