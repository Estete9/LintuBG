package com.naughtsmt.lintu.presentation.game_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naughtsmt.lintu.common.Constants.ALL_GAMES_LIST_ID
import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.domain.use_case.get_game_list.GetTopGamesListUseCase
import com.naughtsmt.lintu.domain.use_case.get_single_list.GetSingleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val getTopGamesListUseCase: GetTopGamesListUseCase,
    private val getSingleListUseCase: GetSingleListUseCase
) : ViewModel() {
    private val _state = mutableStateOf(GameListState())
    val state: State<GameListState> = _state


//    init {
//        getGamesFromMainList(ALL_GAMES_LIST_ID)
//    }

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