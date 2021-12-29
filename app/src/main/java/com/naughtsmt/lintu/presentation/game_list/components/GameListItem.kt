package com.naughtsmt.lintu.presentation.game_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.naughtsmt.lintu.common.Constants.DEFAULT_IMAGE
import com.naughtsmt.lintu.data.repository.model.Game
import com.naughtsmt.lintu.presentation.lists.components.ItemDropDownMenu
import com.naughtsmt.lintu.presentation.util.loadPicture

@Composable
fun GameListItem(
    game: Game,
    onItemClicked: (Game) -> Unit,
    onDeleteClicked: (Game) -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = TopCenter

    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClicked(game) },
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp
        ) {
            Box(
                Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = game.name,
                        style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable { onItemClicked(game) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            Modifier
                                .height(100.dp)
                                .fillMaxWidth(0.25f),
                        ) {

                            game.image_url.let { url ->
                                val image =
                                    loadPicture(url = url, defaultImage = DEFAULT_IMAGE).value
                                image?.let { img ->
                                    Image(
                                        bitmap = img.asImageBitmap(),
                                        contentDescription = "${game.name} image",
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        }
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Text(
                                    text = "Dificultad: ${game.average_learning_complexity.toInt()} / 5",
                                    style = MaterialTheme.typography.subtitle2,
                                    fontStyle = FontStyle.Italic
                                )
                                Text(
                                    text = "Duración: ${game.min_playtime.toInt()}",
                                    style = MaterialTheme.typography.subtitle2,
                                    fontStyle = FontStyle.Italic
                                )
                            }
                            Text(
                                text = "Max. Jugadores: ${game.max_players}",
                                textAlign = TextAlign.End,
                                style = MaterialTheme.typography.subtitle1,
                                fontStyle = FontStyle.Italic
                            )
                            Text(
                                modifier = Modifier.align(Start),
                                text = "Descripción:",
                                textAlign = TextAlign.Justify,
                                style = MaterialTheme.typography.subtitle1
                            )
                            Text(
                                modifier = Modifier.align(CenterHorizontally),
                                text = game.description_preview,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Justify,
                                style = MaterialTheme.typography.body2

                            )

                        }
                    }
                }
            }
        }
        Box(
            Modifier
                .fillMaxSize()
                .padding(16.dp), contentAlignment = TopEnd
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Top
            ) {
                ItemDropDownMenu(game, onDeleteClicked)
            }
        }

    }
}
