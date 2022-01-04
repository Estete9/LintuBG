package com.naughtsmt.lintu.presentation.game_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.naughtsmt.lintu.presentation.Screens
import com.naughtsmt.lintu.presentation.game_detail.components.GameDetails
import com.naughtsmt.lintu.presentation.game_detail.components.Tag
import com.naughtsmt.lintu.presentation.scaffold.MainViewModel

@Composable
fun GameDetailScreen(
    navController: NavController,
    viewModel: GameDetailViewModel = hiltViewModel(),
    modifier: Modifier,
    mainViewModel: MainViewModel
) {
    val state = viewModel.state.value
//    mainViewModel.setCurrentScreen(Screens.GameDetailScreen)
    Surface(color = MaterialTheme.colors.background) {
Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = modifier.fillMaxSize()) {

            state.game?.let { game ->
                mainViewModel.currentGameDetailId.value = game.id
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = CenterHorizontally
                        ) {
                            GameDetails(
                                game = game,
                                contentDescription = "${game.name} image",
                                title = game.name,
                                modifier = Modifier,
                                onItemClicked = {
                                    navController.navigate(Screens.ImageScreen.route)
                                }
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                        }
                    }
//                item {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(horizontal = 8.dp),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                    ) {
//
//                        Text(
//                            text = "Dificultad: ${game.average_learning_complexity.toInt()} / 5",
//                            style = MaterialTheme.typography.subtitle1,
//                            fontStyle = FontStyle.Italic,
//                        )
//                        Text(
//                            text = "Duración: ${game.min_playtime.toInt()}",
//                            style = MaterialTheme.typography.subtitle1,
//                            fontStyle = FontStyle.Italic,
//                            textAlign = TextAlign.End
//                        )
//                    }
//                    Text(
//                        text = "Max. jugadores: ${game.max_players}",
//                        style = MaterialTheme.typography.subtitle1,
//                        fontStyle = FontStyle.Italic
//                    )
//                }
//                item {
//                    Spacer(modifier = Modifier.height(20.dp))
//                    ExpandableText(
//                        text = game.description_preview,
//                        modifier = Modifier,
//                        minimizedMaxLines = 4
////                        style = MaterialTheme.typography.body1,
////                        textAlign = TextAlign.Justify
//                    )
                    item {

                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "Categorías",
                            style = MaterialTheme.typography.h5
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        FlowRow(
                            mainAxisSpacing = 5.dp,
                            crossAxisSpacing = 10.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (game.categories.size > 4) {
                                repeat(5) {
                                    Tag(tag = game.categories[it])
                                }
                            } else game.categories.forEach { Tag(tag = it) }

//                        game.categories.forEach { tag -> Tag(tag = tag) }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Divider(
                            color = MaterialTheme.colors.secondaryVariant,
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth(0.9f)
                        )
                        Spacer(modifier = Modifier.height(18.dp))
                        Text(
                            text = "Mecánicas",
                            style = MaterialTheme.typography.h5
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        FlowRow(
                            mainAxisSpacing = 10.dp,
                            crossAxisSpacing = 10.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (game.mechanics.size > 4) {
                                repeat(5) {
                                    Tag(tag = game.mechanics[it])
                                }
                            } else game.mechanics.forEach { Tag(tag = it) }
//                        game.mechanics.forEach { tag -> Tag(tag = tag) }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Divider(
                            color = MaterialTheme.colors.secondaryVariant,
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth(0.9f)
                        )
                    }
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

}