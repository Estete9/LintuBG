package com.naughtsmt.lintu.presentation.single_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.naughtsmt.lintu.common.Constants.DEFAULT_IMAGE
import com.naughtsmt.lintu.data.repository.model.Game
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

//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable { onItemClicked(game) },
//            shape = RoundedCornerShape(10.dp),
//            elevation = 5.dp
//        ) {
        Box(
            Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = CenterHorizontally
            ) {


                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { onItemClicked(game) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier
                            .height(80.dp)
//                            .height(100.dp)
                            .fillMaxWidth(0.25f),
                    ) {

                        game.image_url.let { url ->
                            val image =
                                loadPicture(url = url, defaultImage = DEFAULT_IMAGE).value
                            image?.let { img ->
                                Image(
                                    bitmap = img.asImageBitmap(),
                                    contentDescription = "${game.name} image",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.clip(RoundedCornerShape(20.dp))
                                )
                            }
                        }
                    }
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        FlowRow(
                            mainAxisSpacing = 5.dp,
                            crossAxisSpacing = 10.dp,
                            modifier = Modifier.fillMaxWidth()
                        ){

                            Text(
//                                modifier = Modifier.fillMaxWidth(),
                                text = game.name,
                                style = MaterialTheme.typography.h5,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.primary
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .padding(
                                        horizontal = 6.dp,
                                        vertical = 1.dp
                                    )
                            ) {
                                Surface(
//                                        modifier = Modifier.padding(5.dp),
                                    color = MaterialTheme.colors.secondary,
                                    shape = RoundedCornerShape(15.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .padding(
                                                horizontal = 8.dp,
                                                vertical = 1.dp
                                            )
                                    ) {

                                        Text(
                                            "${game.max_players}",
                                            color = MaterialTheme.colors.onSecondary,
                                            style = MaterialTheme.typography.subtitle2
                                        )
                                        Spacer(modifier = Modifier.width(2.dp))
                                        Icon(
                                            modifier = Modifier.size(18.dp),
                                            imageVector = Icons.Filled.Person,
                                            contentDescription = "number of players icon",
                                            tint = MaterialTheme.colors.onBackground
                                        )
                                    }
                                }
                            }
                        }
                        Text(
                            modifier = Modifier.align(CenterHorizontally),
                            text = game.description_preview,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Justify,
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.primary

                        )

                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Surface(
                                modifier = Modifier
                                    .padding(
                                        horizontal = 8.dp,
                                        vertical = 1.dp
                                    ),
                                color = MaterialTheme.colors.surface,
                                shape = RoundedCornerShape(15.dp),
                                border = BorderStroke(
                                    1.dp,
                                    color = MaterialTheme.colors.secondaryVariant
                                )
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(
                                            horizontal = 15.dp,
                                            vertical = 3.dp
                                        ),
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier.padding(vertical = 2.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.BarChart,
                                            contentDescription = "icono de dificultad",
                                            modifier = Modifier.size(18.dp),
                                            tint = when {
                                                game.average_learning_complexity > 4.9 -> {
                                                    Color.Red
                                                }
                                                game.average_learning_complexity > 3.9 -> {
                                                    Color.Yellow
                                                }
                                                else -> Color.Green
                                            }
                                        )
                                        Text(
                                            text = if (game.average_learning_complexity < 0.1) "1 / 5"
                                            else "${game.average_learning_complexity.toInt()} / 5",
                                            style = MaterialTheme.typography.subtitle2,
                                            fontStyle = FontStyle.Italic,
                                            color = MaterialTheme.colors.primary
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            Surface(
                                color = MaterialTheme.colors.surface,
                                shape = RoundedCornerShape(15.dp),
                                border = BorderStroke(
                                    1.dp,
                                    color = MaterialTheme.colors.secondaryVariant
                                )
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(
                                            horizontal = 15.dp,
                                            vertical = 3.dp
                                        ),
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier.padding(vertical = 2.dp)
                                    ) {

                                        Icon(
                                            imageVector = Icons.Filled.Timer,
                                            modifier = Modifier.size(18.dp),
                                            contentDescription = "icono de duracion",

                                            )
                                        Text(
                                            text = if (game.min_playtime < 1.0 && game.min_playtime > -1.0) "1 min." else "${game.min_playtime.toInt()} min.",
                                            style = MaterialTheme.typography.subtitle2,
                                            fontStyle = FontStyle.Italic,
                                            color = MaterialTheme.colors.primary
                                        )
                                    }
                                }
                            }
                        }


                    }
                }
            }
        }
    }
}