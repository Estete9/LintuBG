package com.naughtsmt.lintu.presentation.game_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.naughtsmt.lintu.R
import com.naughtsmt.lintu.domain.model.Game
import kotlin.math.nextUp

@Composable
fun GameListItem(
    game: Game,
    onItemClicked: (Game) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClicked(game) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.Start
    ) {
//        TODO implement image loading
        Image(
            painter = painterResource(R.drawable.lintu_logo),
            contentDescription = "GameListPreview"
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .clickable { onItemClicked(game) },
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = game.name,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h3
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClicked(game) },
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Dificultad: ${game.average_learning_complexity.nextUp()}",
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "# Jugadores: ${game.max_players}",
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "Duración: ${game.min_playtime}",
                    style = MaterialTheme.typography.h6
                )

            }
            Text(
                text = game.description_preview,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.align(Start)
            )

        }

    }
}
