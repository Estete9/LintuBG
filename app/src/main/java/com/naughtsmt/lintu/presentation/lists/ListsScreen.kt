package com.naughtsmt.lintu.presentation.lists

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naughtsmt.lintu.presentation.Screen
import com.naughtsmt.lintu.presentation.lists.components.ListItemRow

val tag = "tokenandcode"

@Composable
fun ListsScreen(
    navController: NavController,
    listsViewModel: ListsViewModel = hiltViewModel(),
    authTokenViewModel: AuthPostViewModel = hiltViewModel(),
) {

    val listsState = listsViewModel.state.value
//    Log.d(tag, "code: ${authTokenViewModel.code.value}")
//    authTokenViewModel.getAuthToken(code = authTokenViewModel.code.value)
    val authTokenState = authTokenViewModel.state.value
    if (authTokenState.isLoading){

    Log.d(tag, "authTokenState is loading")
    }
    if (!authTokenState.isLoading){

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
                            navController.navigate(Screen.SingleListScreen.route + "&${list.id}")
                        }
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                }
            }
        }
    }
}