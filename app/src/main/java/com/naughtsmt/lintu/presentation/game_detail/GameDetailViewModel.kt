package com.naughtsmt.lintu.presentation.game_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naughtsmt.lintu.common.Constants
import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.domain.use_case.get_game.GetGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val getGameUseCase: GetGameUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(GameDetailState())
    val state: State<GameDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_GAME_ID)?.let { gameId ->
            getGameDetail(gameId)
        }
    }

    private fun getGameDetail(gameId: String) {
        getGameUseCase(gameId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = GameDetailState(game = result.data?.get(0))
                }
                is Resource.Error -> {
                    _state.value =
                        GameDetailState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = GameDetailState(isLoading = true)
                }

            }

        }.launchIn(viewModelScope)
    }
}