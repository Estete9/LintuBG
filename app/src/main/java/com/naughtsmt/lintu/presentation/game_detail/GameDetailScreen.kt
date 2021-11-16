package com.naughtsmt.lintu.presentation.game_detail

import GameDetailViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.naughtsmt.lintu.common.Constants.DEFAULT_IMAGE
import com.naughtsmt.lintu.presentation.game_detail.components.Tag
import com.naughtsmt.lintu.presentation.loadPicture

@Composable
fun GameListScreen(
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
                        verticalArrangement = Arrangement.Top
                    ) {
                        game.image_url.let { url ->
                            val image = loadPicture(url = url, defaultImage = DEFAULT_IMAGE).value
//                            TODO add content description
                            image?.asImageBitmap()
                                ?.let { Image(bitmap = it, contentDescription = "TODO") }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {

                            Text(
                                text = game.name,
                                style = MaterialTheme.typography.h1,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(8f)
                            )
                            Text(
                                text = game.rank.toString(),
                                textAlign = TextAlign.End,
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier
                                    .align(CenterVertically)
                                    .weight(2f)
                            )
                        }

                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {

                        Text(
                            text = "Dificultad: ${game.average_learning_complexity}",
                            style = MaterialTheme.typography.subtitle1,
                            fontStyle = FontStyle.Italic,
                        )
                        Text(
                            text = "Duración: ${game.min_playtime}",
                            style = MaterialTheme.typography.subtitle1,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Number of players: ${game.max_players}",
                        style = MaterialTheme.typography.subtitle1
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = game.description_preview,
                        style = MaterialTheme.typography.body1,
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Categorías",
                        style = MaterialTheme.typography.h3
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        game.categories.forEach { tag -> Tag(category = tag) }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Mecánicas",
                        style = MaterialTheme.typography.h3
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        game.mechanics.forEach { tag -> Tag(category = tag) }
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