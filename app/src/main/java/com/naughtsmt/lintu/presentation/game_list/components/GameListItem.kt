package com.naughtsmt.lintu.presentation.game_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.naughtsmt.lintu.R
import com.naughtsmt.lintu.data.repository.model.Game
import com.naughtsmt.lintu.presentation.loadPicture
import kotlin.math.nextUp

@Composable
fun GameListItem(
    game: Game,
    onItemClicked: (Game) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClicked(game) }
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        game.image_url.let { url ->
            val image = loadPicture(url = url/*, defaultImage = Constants.DEFAULT_IMAGE*/).value
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = game.image_url,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.align(CenterHorizontally)
                )
            }

        }
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .clickable { onItemClicked(game) },
            verticalAlignment = Alignment.Top
//            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClicked(game) },
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = game.name,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h3
                )
                Text(
                    text = "Dificultad: ${game.average_learning_complexity.toInt()} / 5",
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "Max. Jugadores: ${game.max_players}",
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "Duración: ${game.min_playtime.toInt()}",
                    style = MaterialTheme.typography.h6
                )

                Text(
                    text = game.description_preview,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.align(CenterHorizontally)
                )
            }

        }

    }
}
