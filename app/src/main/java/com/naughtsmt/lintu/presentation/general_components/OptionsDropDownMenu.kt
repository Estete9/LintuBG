package com.naughtsmt.lintu.presentation.general_components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.naughtsmt.lintu.common.Constants


@Composable
fun ItemDropDownMenu(
    currentScreen: MutableState<String>,
    onDeleteClicked: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }


    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            if (currentScreen.value != Constants.TOP_BAR_RANKING) {

                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "Item options",
                        tint = MaterialTheme.colors.primary
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    DropdownMenuItem(onClick = {
                        onDeleteClicked()
                        expanded = false
                    }) {
                        Text("remover juego")
                    }
                }
            }
        }
    }
}