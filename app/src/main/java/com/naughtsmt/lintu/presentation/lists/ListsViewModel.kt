package com.naughtsmt.lintu.presentation.lists

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.domain.use_case.get_lists.GetListsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListsViewModel @Inject constructor(
    val listsUseCase: GetListsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ListsState())
    val state: State<ListsState> = _state

    init {
        getLists()
    }

    private fun getLists() {
        listsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ListsState(lists = result.data ?: emptyList())
                }

                is Resource.Loading -> {
                    _state.value =
                        ListsState(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Error -> {
                    _state.value = ListsState(isLoading = true)
                }
            }
        }
    }
}