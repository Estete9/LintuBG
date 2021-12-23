package com.naughtsmt.lintu.presentation.scaffold

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.domain.use_case.add_game_to_list.AddGameToListUseCase
import com.naughtsmt.lintu.presentation.game_detail.AddGameToListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addGameToListUseCase: AddGameToListUseCase,
) : ViewModel() {
    private val _addedGameState = mutableStateOf(AddGameToListState())
    val addGameState: State<AddGameToListState> = _addedGameState
    val currentGameId = mutableStateOf("")
    val currentListId = mutableStateOf("")

    fun addGameToList(listId: String, gameId: String) {
        addGameToListUseCase(listId, gameId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _addedGameState.value =
                        AddGameToListState(operationResult = result.data?.success ?: false)
                }
                is Resource.Loading -> {
                    _addedGameState.value = AddGameToListState(isLoading = true)
                }
                is Resource.Error -> {
                    _addedGameState.value =
                        AddGameToListState(error = result.message ?: "An unexpected error occurred")
                }
            }

        }.launchIn(viewModelScope)
    }

}