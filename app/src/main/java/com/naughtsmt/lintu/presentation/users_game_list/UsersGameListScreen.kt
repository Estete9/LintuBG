package com.naughtsmt.lintu.presentation.users_game_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.naughtsmt.lintu.presentation.Screen
import com.naughtsmt.lintu.presentation.game_list.components.GameListItem


@Composable
fun UsersGameListScreen(
    navController: NavController,
    viewModel: UsersGameListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primaryVariant)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            /*verticalArrangement = Arrangement.spacedBy(8.dp)*/
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
                            .align(Alignment.Center)
                    ) {
                        Text(
                            text = "¿Qué vamos a jugar hoy?",
                            modifier = Modifier.align(Alignment.BottomCenter),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.h3,
                            fontWeight = FontWeight.Bold,
                            fontSize = 40.sp
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
                        navController.navigate(Screen.GameDetailScreen.route + "&${game.id}")
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