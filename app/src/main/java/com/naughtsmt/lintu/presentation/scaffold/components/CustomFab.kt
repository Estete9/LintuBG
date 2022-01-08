package com.naughtsmt.lintu.presentation.scaffold.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable

@Composable
fun CustomFab(showAddGameDropDownMenu: () -> Unit) {
    FloatingActionButton(onClick = { showAddGameDropDownMenu() }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "add game floating button")
    }
}