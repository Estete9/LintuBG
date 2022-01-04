package com.naughtsmt.lintu.presentation.single_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naughtsmt.lintu.common.Constants
import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.domain.use_case.get_game_by_name.GetGameByNameUseCase
import com.naughtsmt.lintu.domain.use_case.get_game_list.GetTopGamesListUseCase
import com.naughtsmt.lintu.domain.use_case.get_single_list.GetSingleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SingleListViewModel @Inject constructor(
    val getSingleListUseCase: GetSingleListUseCase,
    val getGameByNameUseCase: GetGameByNameUseCase,
    val getTopGamesListUseCase: GetTopGamesListUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(GameListState())
    val state: State<GameListState> = _state


    //    TODO find a way to pass the list id
    init {
        savedStateHandle.get<String>(Constants.PARAM_SINGLE_LIST_ID)?.let { singleListId ->
            getSingleList(singleListId)
        }
        savedStateHandle.get<String>(Constants.PARAM_SEARCH_BY_NAME_LIST)?.let { name ->
            getGameByName(name)
        }
    }

    fun getSingleList(list_id: String) {
        getSingleListUseCase(list_id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = GameListState(games = result.data ?: emptyList())

                }
                is Resource.Error -> {
                    _state.value =
                        GameListState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = GameListState(isLoading = true)
                }

            }

        }.launchIn(viewModelScope)
    }

    private fun getGameByName(name: String) {
        getGameByNameUseCase(name).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = GameListState(games = result.data ?: emptyList())

                }
                is Resource.Error -> {
                    _state.value =
                        GameListState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = GameListState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }

    fun getGamesFromMainList(listId: String) {
        getSingleListUseCase(listId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = GameListState(games = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = GameListState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value =
                        GameListState(error = result.message ?: "An unexpected error occurred")
                }
            }

        }.launchIn(viewModelScope)

    }

    fun getTopGamesList() {
        getTopGamesListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = GameListState(games = result.data ?: emptyList())

                }
                is Resource.Loading -> {
                    _state.value = GameListState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value =
                        GameListState(error = result.message ?: "An unexpected error occurred")
                }

            }

        }.launchIn(viewModelScope)
    }
}