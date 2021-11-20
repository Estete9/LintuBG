package com.naughtsmt.lintu.presentation.game_detail.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Tag(tag: String) {
    Box(
        modifier = Modifier
            .border(
                shape = RoundedCornerShape(100.dp),
                width = 1.dp,
                color = MaterialTheme.colors.primary
            )
            .padding(20.dp)

    ) {
        Text(
            text = tag,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}