package com.naughtsmt.lintu.presentation.scaffold

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.domain.use_case.add_game_to_list.AddGameToListUseCase
import com.naughtsmt.lintu.domain.use_case.delete_game_from_list.DeleteGameFromListUseCase
import com.naughtsmt.lintu.domain.use_case.get_game_by_name.GetGameByNameUseCase
import com.naughtsmt.lintu.presentation.game_detail.AddGameToListState
import com.naughtsmt.lintu.presentation.game_detail.GameDetailState
import com.naughtsmt.lintu.presentation.scaffold.states.DeleteGameFromListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addGameToListUseCase: AddGameToListUseCase,
//    private val searchGameByNameUseCase: GetGameByNameUseCase,
    val deleteGameFromListUseCase: DeleteGameFromListUseCase,
) : ViewModel() {
    private val _addedGameState = mutableStateOf(AddGameToListState())
    val addGameState: State<AddGameToListState> = _addedGameState

    private val _searchedGameState = mutableStateOf(GameDetailState())
    val searchedGameState: State<GameDetailState> = _searchedGameState

    private val _deleteGameFromListState = mutableStateOf(DeleteGameFromListState())
    val deleteGameFromListState: State<DeleteGameFromListState> = _deleteGameFromListState

    val currentGameDetailId = mutableStateOf("")
    val currentSelectedListId = mutableStateOf("")

    val isDropDownMenuShowed = mutableStateOf(false)

    val refreshState = mutableStateOf(false)

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


//    fun searchGameByName(name: String) {
//        searchGameByNameUseCase(name).onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _searchedGameState.value = GameDetailState(game = result.data?.get(0))
//                }
//
//                is Resource.Loading -> {
//                    _searchedGameState.value = GameDetailState(isLoading = true)
//                }
//                is Resource.Error -> {
//                    _searchedGameState.value =
//                        GameDetailState(error = result.message ?: "An unexpected error occurred")
//                }
//            }
//        }.launchIn(viewModelScope)
//    }

    fun deleteGameFromList(listId: String, gameId: String) {
        deleteGameFromListUseCase(listId, gameId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _deleteGameFromListState.value =
                        result.data?.let { DeleteGameFromListState(operationResult = it.success) }!!
                    refreshState.value = true
                }
                is Resource.Loading -> {
                    _deleteGameFromListState.value = DeleteGameFromListState(isLoading = true)
                }
                is Resource.Error -> {
                    _deleteGameFromListState.value = DeleteGameFromListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}