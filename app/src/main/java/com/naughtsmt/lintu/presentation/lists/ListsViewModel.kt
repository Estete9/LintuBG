package com.naughtsmt.lintu.presentation.lists

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.domain.use_case.create_list.CreateListUseCase
import com.naughtsmt.lintu.domain.use_case.delete_list.DeleteListUseCase
import com.naughtsmt.lintu.domain.use_case.get_lists.GetListsUseCase
import com.naughtsmt.lintu.presentation.lists.components.Lists
import com.naughtsmt.lintu.presentation.lists.states.DeleteListState
import com.naughtsmt.lintu.presentation.lists.states.ListChangeState
import com.naughtsmt.lintu.presentation.lists.states.ListsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

val thisTag = "GetListsViewModel"

@HiltViewModel
class ListsViewModel @Inject constructor(
    private val listsUseCase: GetListsUseCase,
    private val newListUseCase: CreateListUseCase,
    private val deleteListUseCase: DeleteListUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ListsState())
    val state: State<ListsState> = _state

    private val _newListState = mutableStateOf(ListChangeState())
    val listChangeState: State<ListChangeState> = _newListState

    private val _deleteListState = mutableStateOf(DeleteListState())
    val deleteListState: State<DeleteListState> = _deleteListState

    init {
        getLists()
//        Log.d(thisTag, "getLists fun from GetListsViewModel called")
    }

    fun getLists() {
        listsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ListsState(lists = result.data ?: emptyList())
                    Lists.lists.value = _state.value.lists
//                    Log.d(thisTag, "getLists in viewmodel success")
                }

                is Resource.Loading -> {
                    _state.value = ListsState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value =
                        ListsState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }


    fun makeNewList(listName: String) {
        newListUseCase(listName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _newListState.value = ListChangeState(newList = result.data?.list)
                }
                is Resource.Loading -> {
                    _newListState.value = ListChangeState(isLoading = true)
                }
                is Resource.Error -> {
                    _newListState.value =
                        ListChangeState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }


    fun deleteList(listId: String) {
        deleteListUseCase(listId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _deleteListState.value =
                        DeleteListState(operationResult = result.data?.success ?: false)
                }
                is Resource.Loading -> {
                    _deleteListState.value = DeleteListState(isLoading = true)
                }
                is Resource.Error -> {
                    _deleteListState.value =
                        DeleteListState(error = result.message ?: "An unexpected error occurred")
                }
            }

        }.launchIn(viewModelScope)
    }
}