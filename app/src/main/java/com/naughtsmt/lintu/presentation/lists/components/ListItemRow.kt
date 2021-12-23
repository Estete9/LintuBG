package com.naughtsmt.lintu.presentation.lists.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naughtsmt.lintu.common.Constants.ALL_GAMES_LIST_ID
import com.naughtsmt.lintu.data.data_source.lists_dto.Lists

@Composable
fun ListItemRow(
    list: Lists,
    listNumber: Int,
    onItemClicked: (String) -> Unit,
    onDeleteClicked: (String) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onItemClicked(list.id) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column() {
            Text(
                text = "$listNumber. ${list.name}",
                style = MaterialTheme.typography.h2,
                fontSize = 21.sp
            )
            Text(text = "Juegos: ${list.gameCount}", style = MaterialTheme.typography.body2)
        }
        if (list.id != ALL_GAMES_LIST_ID) {
            Button(onClick = { onDeleteClicked(list.id) }) {
                Icon(imageVector = Icons.Filled.Remove, contentDescription = "Remove List Icon")
            }
        }
    }
}