package com.naughtsmt.lintu.presentation.lists

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naughtsmt.lintu.presentation.Screens
import com.naughtsmt.lintu.presentation.lists.components.ListItemRow

const val tag = "tokenAndCode"

@Composable
fun ListsScreen(
    navController: NavController,
    listsViewModel: ListsViewModel = hiltViewModel(),
    authTokenViewModel: AuthPostViewModel = hiltViewModel(),
    modifier: Modifier
) {

    val listsState = listsViewModel.state.value
    val authTokenState = authTokenViewModel.state.value
    if (authTokenState.isLoading) {

        Log.d(tag, "authTokenState is loading")
    }
    if (!authTokenState.isLoading) {

        Log.d(tag, "this is the auth token: ${authTokenState.accessToken}")
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {

            if (listsState.lists.isNullOrEmpty()) {
                item {
                    Text(text = "there are no lists")
                }
            } else {
                items(listsState.lists) { list ->
                    ListItemRow(
                        list = list,
                        onItemClicked = {
                            navController.navigate(Screens.SingleListScreen.route + "&${list.id}")
                        }
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                }
            }
            item {
                Button(
                    onClick =
                    { navController.navigate(Screens.GameListScreen.route) }
                ) {
                    Text(text = "go to top game list")
                }
            }
        }
    }
}