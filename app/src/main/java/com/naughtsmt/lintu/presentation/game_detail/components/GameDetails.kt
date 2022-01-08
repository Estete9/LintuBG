package com.naughtsmt.lintu.presentation.game_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.naughtsmt.lintu.common.Constants.DEFAULT_IMAGE
import com.naughtsmt.lintu.data.repository.model.Game
import com.naughtsmt.lintu.presentation.general_components.ExpandableText
import com.naughtsmt.lintu.presentation.util.loadPicture

@Composable
fun GameDetails(
    game: Game,
    contentDescription: String,
    title: String,
    modifier: Modifier,
) {
    Box(
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                Modifier
                    .fillMaxWidth(0.25f)
                    .height(80.dp)
            ) {

                game.image_url.let { url ->
                    val image = loadPicture(url = url, defaultImage = DEFAULT_IMAGE).value
                    image?.let { img ->
                        Image(
                            modifier = modifier.clip(RoundedCornerShape(20.dp)),
                            bitmap = img.asImageBitmap(),
                            contentDescription = contentDescription,
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                FlowRow(
                    mainAxisAlignment = MainAxisAlignment.Start,
                    crossAxisAlignment = FlowCrossAxisAlignment.Center,
                    mainAxisSpacing = 5.dp,
                    crossAxisSpacing = 10.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h5,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(
                                horizontal = 6.dp,
                                vertical = 1.dp
                            )
                    ) {

                        Surface(
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
                                    "${game.min_players}-${game.max_players}",
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
                Spacer(modifier = Modifier.height(20.dp))
                ExpandableText(
                    text = game.description_preview,
                    modifier = Modifier,
                    minimizedMaxLines = 6,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.height(15.dp))
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
                                        game.average_learning_complexity > 2.9 -> {
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.BottomStart
        ) {
        }
    }

}