package com.naughtsmt.lintu.presentation.game_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.naughtsmt.lintu.presentation.Screen
import com.naughtsmt.lintu.presentation.game_detail.components.GameCard
import com.naughtsmt.lintu.presentation.game_detail.components.Tag

@Composable
fun GameDetailScreen(
    navController: NavController,
    viewModel: GameDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {

        state.game?.let { game ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                item {
//TODO
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = CenterHorizontally
                    ) {
                        GameCard(
                            game = game,
                            contentDescription = "${game.name} image",
                            title = game.name,
                            modifier = Modifier,
                            onItemClicked = {
                                navController.navigate(Screen.ImageScreen.route)
                            }
                        )
//                        game.image_url.let { url ->
//                            val image =
//                                loadPicture(url = url, defaultImage = DEFAULT_IMAGE).value
////                            TODO add content description
//                            image?.let {
//                                Image(
//                                    bitmap = it.asImageBitmap(),
//                                    contentDescription = "TODO",
//                                    contentScale = ContentScale.FillHeight,
//                                    modifier = Modifier.align(CenterHorizontally)
//                                )
//                            }
//                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {

///                            Text(
//                                text = game.rank.toString(),
//                                textAlign = TextAlign.End,
//                                style = MaterialTheme.typography.subtitle1,
//                                fontStyle = FontStyle.Italic,
//                                modifier = Modifier
//                                    .align(CenterVertically)
//                                    .weight(2f)
//                            )
                        }

                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {

                        Text(
                            text = "Dificultad: ${game.average_learning_complexity.toInt()} / 5",
                            style = MaterialTheme.typography.subtitle1,
                            fontStyle = FontStyle.Italic,
                        )
                        Text(
                            text = "Duración: ${game.min_playtime.toInt()}",
                            style = MaterialTheme.typography.subtitle1,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End
                        )
                    }
                    Text(
                        text = "Max. jugadores: ${game.max_players}",
                        style = MaterialTheme.typography.subtitle1,
                        fontStyle = FontStyle.Italic
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = game.description_preview,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Justify
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Categorías",
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    FlowRow(
                        mainAxisSpacing = 5.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        game.categories.forEach { tag -> Tag(tag = tag) }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Mecánicas",
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        game.mechanics.forEach { tag -> Tag(tag = tag) }
                    }
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