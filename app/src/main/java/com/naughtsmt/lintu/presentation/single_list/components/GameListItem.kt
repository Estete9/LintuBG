package com.naughtsmt.lintu.presentation.single_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.naughtsmt.lintu.common.Constants
import com.naughtsmt.lintu.common.Constants.DEFAULT_IMAGE
import com.naughtsmt.lintu.data.repository.model.Game
import com.naughtsmt.lintu.presentation.scaffold.MainViewModel
import com.naughtsmt.lintu.presentation.util.loadPicture

@Composable
fun GameListItem(
    game: Game,
    onItemClicked: () -> Unit,
    currentScreen: MutableState<String>,
    onDeleteClicked: () -> Unit,
    ShowAddGameDropDownMenu: () -> Unit,
    isEditing: Boolean,
    finishedEditing: () -> Unit,
    refresh: () -> Unit,
    mainViewModel: MainViewModel
) {
    val needsRefresh = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        if (needsRefresh.value)
            refresh()
        needsRefresh.value = false
    }
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = TopCenter

    ) {

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
                        .clickable { onItemClicked() },
                    verticalAlignment = CenterVertically
                ) {
                    Box(
                        Modifier
                            .height(80.dp)
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
                        Row {

                            FlowRow(
                                mainAxisSpacing = 5.dp,
                                crossAxisSpacing = 10.dp,
                                mainAxisAlignment = MainAxisAlignment.Start,
                                crossAxisAlignment = FlowCrossAxisAlignment.Center,
                                modifier = Modifier.fillMaxWidth(0.8f)
                            ) {

                                Text(
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
                                        color = MaterialTheme.colors.secondary,
                                        shape = RoundedCornerShape(15.dp)
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = CenterVertically,
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
                            if (isEditing) {
                                IconButton(
                                    onClick = {
                                        if (currentScreen.value == Constants.SEARCH_RESUlTS) {
                                            mainViewModel.currentGameDetailId.value = game.id
                                            ShowAddGameDropDownMenu()
                                        } else {
                                            onDeleteClicked()
                                            refresh()
                                        }
                                    },
                                    modifier = Modifier
                                        .size(20.dp)
                                        .offset(x = 5.dp, y = 7.dp)
                                        .border(
                                            width = 1.dp,
                                            color = if (currentScreen.value == Constants.SEARCH_RESUlTS) {
                                                Color.Green
                                            } else {
                                                Color.Red
                                            },
                                            shape = CircleShape
                                        ),
                                ) {
                                    if (currentScreen.value == Constants.SEARCH_RESUlTS) {
                                        Icon(
                                            imageVector = Icons.Filled.Add,
                                            tint = Color.Green,
                                            contentDescription = "add game to list icon"
                                        )
                                    } else {

                                        Icon(
                                            imageVector = Icons.Filled.Close,
                                            tint = Color.Red,
                                            contentDescription = "delete game from list icon"
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
                                        verticalAlignment = CenterVertically,
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
                                        verticalAlignment = CenterVertically,
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier.padding(vertical = 2.dp)
                                    ) {

                                        Icon(
                                            imageVector = Icons.Filled.Timer,
                                            modifier = Modifier.size(18.dp),
                                            contentDescription = "icono de duracion",

                                            )
                                        Text(
                                            text = if (game.min_playtime < 1.0 && game.min_playtime.toInt() > -1.0) "1-${game.max_playtime.toInt()} min."
                                            else "${game.min_playtime.toInt()}-${game.max_playtime.toInt()} min.",
                                            style = MaterialTheme.typography.subtitle2,
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


