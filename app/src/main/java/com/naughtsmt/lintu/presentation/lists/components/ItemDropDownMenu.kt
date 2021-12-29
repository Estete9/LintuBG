package com.naughtsmt.lintu.presentation.lists.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.naughtsmt.lintu.data.repository.model.Game

@Composable
fun ItemDropDownMenu(
    game: Game,
    onDeleteClicked: (Game) -> Unit
) {
//    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }


    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            IconButton(onClick = { expanded = !expanded }) {
                Icon(imageVector = Icons.Filled.MoreHoriz, contentDescription = "Item options")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
//                modifier = Modifier.fillMaxWidth()
            ) {
                DropdownMenuItem(onClick = {
                    expanded = false
                    onDeleteClicked(game)
                }) {
                    Text("remover juego")
                }
            }

        }
    }


}

//@Preview(showBackground = true)
//@Composable
//fun GamListDropDownMenuPreview() {
//    ItemDropDownMenu()
//}