package com.naughtsmt.lintu.presentation.scaffold.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.naughtsmt.lintu.common.Constants
import com.naughtsmt.lintu.presentation.Screens

@Composable
fun TopBar(
    navController: NavController,
    toTopGames: () -> Unit,
    toAllGames: () -> Unit,
    focusCleared: MutableState<Boolean>,
    currentScreen: MutableState<String>
) {
    val searchBarShown = remember { mutableStateOf(false) }
    val searchGameText = remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val icon = remember { mutableStateOf(Icons.Filled.Search) }
    Log.d(tag, "1focus cleared is: ${focusCleared.value}")
    TopAppBar(backgroundColor = Color.Transparent) {
        Box(
            Modifier
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            DisposableEffect(key1 = focusCleared.value) {
                Log.d(tag, "2focus cleared is: ${focusCleared.value}")
                if (focusCleared.value) {
                    Log.d(tag, "3focus cleared is: ${focusCleared.value}")
                    searchBarShown.value = false
                }
                onDispose { }

            }
            if (searchBarShown.value) {
                Box {

                    TextField(
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(onSearch = {
                            Log.d(tag, "4focus cleared is: ${focusCleared.value}")
                            searchBarShown.value = !searchBarShown.value
                            focusCleared.value = !focusCleared.value
                            Log.d(tag, "5focus cleared is: ${focusCleared.value}")
                            Log.d(tag, "-------------------------")
                            if (searchGameText.value.isNotBlank()) {
                                navController
                                    .navigate(Screens.SingleListScreen.route + "?&name=${searchGameText.value}")
                                searchGameText.value = ""
                                icon.value = Icons.Filled.Search
                                currentScreen.value = Constants.SEARCH_RESUlTS
                            }
                        }),
                        value = searchGameText.value,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.onPrimary
                        ),
                        onValueChange = { searchGameText.value = it },
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .focusRequester(focusRequester = focusRequester)
                            .align(Alignment.CenterStart)
                    )
                    DisposableEffect(key1 = Unit) {
                        focusRequester.requestFocus()
                        icon.value = Icons.Filled.Send
                        onDispose { }
                    }
                }
            }


            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!searchBarShown.value) {
                    Text(text = Constants.TOP_BAR_JUEGOS,
                        color = MaterialTheme.colors.primary,
                        fontWeight = if (currentScreen.value == Constants.TOP_BAR_JUEGOS) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Normal
                        },
                        fontSize = if (currentScreen.value == Constants.TOP_BAR_JUEGOS) {
                            18.sp
                        } else {
                            16.sp
                        },
                        modifier = Modifier.clickable {
                            currentScreen.value = Constants.TOP_BAR_JUEGOS
                            toAllGames()
                            navController.navigate(Screens.SingleListScreen.route) /*{
                            launchSingleTop = true
                        }*/
                        })

                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                    Divider(
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier
                            .height(15.dp)
                            .width(1.dp)
                    )

                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                    Text(text = Constants.TOP_BAR_RANKING,
                        color = MaterialTheme.colors.primary,
                        fontWeight = if (currentScreen.value == Constants.TOP_BAR_RANKING) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Normal
                        },
                        fontSize = if (currentScreen.value == Constants.TOP_BAR_RANKING) {
                            18.sp
                        } else {
                            16.sp
                        },
                        modifier = Modifier.clickable {
                            currentScreen.value = Constants.TOP_BAR_RANKING
                            toTopGames()
                            navController.navigate(Screens.SingleListScreen.route)
                        })
                }

            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon.value = when {
                    searchGameText.value.isNotBlank() -> {
                        Icons.Filled.Send
                    }
                    searchGameText.value.isBlank() -> {
                        Icons.Filled.Search
                    }
                    focusCleared.value -> {
                        Icons.Filled.Search
                    }
                    else -> {
                        Icons.Filled.Search
                    }
                }
                Icon(
                    imageVector = icon.value,
                    tint = MaterialTheme.colors.secondary,
                    modifier = Modifier.clickable {
                        Log.d(tag, "4focus cleared is: ${focusCleared.value}")
                        searchBarShown.value = !searchBarShown.value
                        focusCleared.value = !focusCleared.value
                        Log.d(tag, "5focus cleared is: ${focusCleared.value}")
                        Log.d(tag, "-------------------------")
                        if (searchGameText.value.isNotBlank()) {
                            navController
                                .navigate(Screens.SingleListScreen.route + "?&name=${searchGameText.value}")
                            searchGameText.value = ""
                            icon.value = Icons.Filled.Search
                        }
                    },
                    contentDescription = "Search or Send Icon"
                )
            }
        }
    }

}
