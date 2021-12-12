package com.naughtsmt.lintu.presentation.lists.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.naughtsmt.lintu.data.data_source.lists_dto.Lists

@Composable
fun ListItemRow(list: Lists, onItemClicked: (String) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(21.dp)
            .padding(horizontal = 16.dp)
            .clickable { onItemClicked(list.id) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = "1. ${list.name}", style = MaterialTheme.typography.h2)
        Text(text = "Juegos: ${list.gameCount}", style = MaterialTheme.typography.body2)
    }
}