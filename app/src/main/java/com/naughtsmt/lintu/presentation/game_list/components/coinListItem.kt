package com.naughtsmt.lintu.presentation.game_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.naughtsmt.lintu.R
import com.naughtsmt.lintu.domain.model.Game
import com.naughtsmt.lintu.presentation.ui.theme.LintuTheme

@Composable
fun coinListItem(
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
        Image(
            painter = painterResource(R.drawable.lintu_logo),
            contentDescription = "GameListPreview"
        )
        Column() {
            Text(
                text = game.name,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}
