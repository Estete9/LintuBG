package com.naughtsmt.lintu.presentation.general_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.naughtsmt.lintu.data.data_source.lists_dto.Lists
import com.naughtsmt.lintu.presentation.lists.components.Lists.lists
import com.naughtsmt.lintu.presentation.scaffold.MainViewModel

@Composable
fun ListsDropDownMenu(
    list: List<Lists> = lists.value,
    addGameToList: () -> Unit,
    mainViewModel: MainViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }

    var textFieldSize by remember { mutableStateOf(Size.Zero) }


    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxSize()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth(4 / 5f)
                    .background(MaterialTheme.colors.surface)
            ) {
                if (selectedItem.isNotBlank()) {
                    Text(
                        text = "Añadirlo a esta lista?",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                ) {
                    Row() {
                        OutlinedTextField(
                            enabled = false,
                            value = selectedItem,
                            onValueChange = { selectedItem = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    textFieldSize = coordinates.size.toSize()
                                },
                            label = { Text("Selecciona la lista") },
                            trailingIcon = {
                                Icon(imageVector = icon,
                                    contentDescription = "drop down menu icon",
                                    modifier = Modifier.clickable { expanded = !expanded })
                            }
                        )
                        DropdownMenu(
                            expanded = expanded, onDismissRequest = { expanded = false },
                            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                        ) {
                            list.forEach { list ->
                                DropdownMenuItem(onClick = {
                                mainViewModel.currentSelectedListId.value = list.id
                                    selectedItem = list.name
                                    expanded = false
                                }) {
                                    Text(text = list.name)
                                }
                            }
                        }
                    }
                    Button(onClick = { addGameToList() }) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Add game to list button"
                        )
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun DropDownMenuPreview() {
//    val testList = Lists(
//        1,
//        "qweqwe",
//        "wewr",
//        Images("afdsdf", "afdsdf", "afdsdf", "afdsdf", "afdsdf"),
//        "test",
//        "3324",
//        "23423"
//    )
//    val testLists = listOf<Lists>(testList, testList, testList)
//    DropDownMenu(true, testLists)
//}