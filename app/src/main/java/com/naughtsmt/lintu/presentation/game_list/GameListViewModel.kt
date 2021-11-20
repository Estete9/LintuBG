package com.naughtsmt.lintu.presentation.game_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.domain.use_case.get_game.GetGameListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val getGameListUseCase: GetGameListUseCase
) : ViewModel() {
    private val _state = mutableStateOf(GameListState())
    val state: State<GameListState> = _state

    init {
        getGameList()
    }

    private fun getGameList() {
        getGameListUseCase().onEach { result ->
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
}