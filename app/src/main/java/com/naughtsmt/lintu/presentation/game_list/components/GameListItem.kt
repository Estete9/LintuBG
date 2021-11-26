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
import androidx.compose.ui.Alignment.Companion.TopCenter
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
import com.naughtsmt.lintu.presentation.loadPicture

@Composable
fun GameListItem(
    game: Game,
    onItemClicked: (Game) -> Unit
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
                Column( modifier = Modifier.padding(8.dp),
                    horizontalAlignment = CenterHorizontally) {
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
//                        contentAlignment = Center
                        ) {

                            game.image_url.let { url ->
                                val image =
                                    loadPicture(url = url, defaultImage = DEFAULT_IMAGE).value
                                image?.let { img ->
                                    Image(
//                                    alignment = Center,
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
                                modifier = Modifier.align(CenterHorizontally),
                                text = "Descripción: ${game.description_preview}",
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Justify,
                                style = MaterialTheme.typography.body2
                            )

                        }
                    }
                }
            }
        }
    }


//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { onItemClicked(game) }
//            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
//        verticalArrangement = Arrangement.SpaceBetween
//    ) {
//
//        game.image_url.let { url ->
//            val image = loadPicture(url = url, defaultImage = DEFAULT_IMAGE).value
//            image?.let { img ->
//                Image(
//                    bitmap = img.asImageBitmap(),
//                    contentDescription = "${game.name} image",
//                    contentScale = ContentScale.FillWidth,
//                    modifier = Modifier.align(CenterHorizontally)
//                )
//            }
//
//        }
//        Row(
//            modifier = Modifier
//                .clickable { onItemClicked(game) },
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 8.dp)
//                    .clickable { onItemClicked(game) },
//                verticalArrangement = Arrangement.SpaceEvenly
//            ) {
//                Text(
//                    modifier = Modifier.fillMaxWidth(),
//                    text = game.name,
//                    style = MaterialTheme.typography.h3,
//                    textAlign = TextAlign.Center,
//                    fontWeight = FontWeight.Bold
//                )
//
//                Spacer(modifier = Modifier.height(15.dp))
//                Row(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//
//                    Text(
//                        text = "Dificultad: ${game.average_learning_complexity.toInt()} / 5",
//                        style = MaterialTheme.typography.subtitle1,
//                        fontStyle = FontStyle.Italic
//                    )
//                    Text(
//                        text = "Duración: ${game.min_playtime.toInt()}",
//                        style = MaterialTheme.typography.subtitle1,
//                        fontStyle = FontStyle.Italic
//                    )
//                }
//                Text(
//                    text = "Max. Jugadores: ${game.max_players}",
//                    textAlign = TextAlign.End,
//                    style = MaterialTheme.typography.subtitle1,
//                    fontStyle = FontStyle.Italic
//                )
//                Spacer(modifier = Modifier.height(20.dp))
//                Text(
//                    modifier = Modifier.align(CenterHorizontally),
//                    text = game.description_preview,
//                    maxLines = 3,
//                    overflow = TextOverflow.Ellipsis,
//                    textAlign = TextAlign.Justify,
//                    style = MaterialTheme.typography.h6
//                )
//            }
//
//        }
//
//    }
//    Spacer(modifier = Modifier.height(30.dp))
}
