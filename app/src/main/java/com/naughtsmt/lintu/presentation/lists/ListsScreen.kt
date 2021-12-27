package com.naughtsmt.lintu.presentation.lists

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naughtsmt.lintu.presentation.Screens
import com.naughtsmt.lintu.presentation.lists.components.ListItemRow
import com.naughtsmt.lintu.presentation.single_list.SingleListViewModel
import kotlinx.coroutines.launch

const val tag = "tokenAndCode"

@Composable
fun ListsScreen(
    modifier: Modifier,
    navController: NavController,
    listsViewModel: ListsViewModel,
    singleListViewModel: SingleListViewModel = hiltViewModel()
) {

    val listsState = listsViewModel.state.value
    val newListState = listsViewModel.listChangeState.value
    val deleteListState = listsViewModel.deleteListState.value
//    val singleListSate = singleListViewModel.state.value

    val scope = rememberCoroutineScope()
    val isEditTextShown = remember { mutableStateOf(false) }
    val newListName = remember { mutableStateOf("") }

    val focusRequester = remember { FocusRequester() }

//    val rememberedNewListState by rememberUpdatedState(newValue = newListState)
//    val rememberedDeleteListState by rememberUpdatedState(newValue = deleteListState)
//    DisposableEffect(key1 = rememberedNewListState.newList) {
//        getListsViewModel.getLists()
//        onDispose { }
//    }
    Box(
        modifier.fillMaxSize(),
        contentAlignment = Center,
    ) {

        LaunchedEffect(key1 = newListState, key2 = deleteListState) {
//    LaunchedEffect(key1 = rememberedNewListState) {
            listsViewModel.getLists()
        }
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                item {
                    Button(
                        onClick = {
                            scope.launch {
                                isEditTextShown.value = true

                                if (newListName.value.isNotEmpty()) {
                                    run {
                                        listsViewModel.makeNewList(newListName.value)
                                    }
                                }
                            }
                        }

//                    { navController.navigate(Screens.GameListScreen.route) }
                    ) {
                        Text(text = "create new list")
                    }
                }
//            if (listsState.lists.isNullOrEmpty()) {
//                item {
//                    Text(text = "there are no lists")
//                }
//            } else {
                items(listsState.lists) { list ->
                    ListItemRow(
                        list = list,
                        listNumber = listsState.lists.indexOf(list) + 1,
                        onItemClicked = if (!isEditTextShown.value) {
                            {
                                singleListViewModel.getSingleList(list_id = list.id)
                                navController.navigate(Screens.SingleListScreen.route + "?&singleListId=${list.id}")
                            }

                        } else {
                            {}
                        },
                        onDeleteClicked = if (!isEditTextShown.value) {
                            { listsViewModel.deleteList(list.id) }
                        } else {
                            {}
                        }
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                }
//                item {
//                    Text(text = newListState.newList?.name.toString())
//                    Text(text = listsState.lists.toString())
//                }
//            }
            }
        }
        if (isEditTextShown.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
//                .background(color = MaterialTheme.colors.background),
                contentAlignment = Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                }
                Card(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth(), elevation = 5.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = SpaceBetween,
                        verticalAlignment = CenterVertically
                    ) {

                        TextField(
                            value = newListName.value,
                            onValueChange = { newListName.value = it },
                            label = { Text("Nombre de la lista") },
                            modifier = Modifier
                                .fillMaxWidth(2 / 3f)
                                .align(CenterVertically)
                                .focusRequester(focusRequester)
                        )
                        DisposableEffect(key1 = Unit) {
                            focusRequester.requestFocus()
                            onDispose { }
                        }
                        Button(
                            onClick = {
                                if (newListName.value.isNotEmpty()) {
                                    run { listsViewModel.makeNewList(newListName.value) }
                                    isEditTextShown.value = false
                                    newListName.value = ""
                                }
                            },
                            modifier = Modifier
                                .clipToBounds()
                                .align(CenterVertically)

                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "add new list",
                                modifier = Modifier.align(CenterVertically)
                            )
                        }
                    }
                }
            }
//                Column(
//                    Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                ) {
//
//                }
        }
        if (listsState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Center))
        }
        if (listsState.error.isNotBlank()) {
            Text(
                text = listsState.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Center)
            )
        }
//        when {
//            newListState.error.isNotBlank() -> {
//                Text(
//                    text = newListState.error,
//                    color = MaterialTheme.colors.error,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 20.dp)
//                        .align(Center)
//                )
//
//            }
//            deleteListState.error.isNotBlank() -> {
//                Text(
//                    text = deleteListState.error,
//                    color = MaterialTheme.colors.error,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 20.dp)
//                        .align(Center)
//                )
//
//            }
//            newListState.isLoading -> {
//                CircularProgressIndicator(modifier = Modifier.align(Center))
//            }
//            deleteListState.isLoading -> {
//                CircularProgressIndicator(modifier = Modifier.align(Center))
//            }
//
//        }
//        if (newListState.error.isNotBlank()) {
//            Text(
//                text = if (newListState.error.isNotBlank()) newListState.error else deleteListState.error,
//                color = MaterialTheme.colors.error,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//                    .align(Alignment.Center)
//            )
//        }
//
//        if (newListState.isLoading) {
//            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//        }
    }


}