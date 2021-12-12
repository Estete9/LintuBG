package com.naughtsmt.lintu.presentation.single_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.domain.use_case.get_single_list.GetSingleListUseCase
import com.naughtsmt.lintu.presentation.game_list.GameListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SingleListViewModel @Inject constructor(
    val getSingleListUseCase: GetSingleListUseCase
) : ViewModel() {
    private val _state = mutableStateOf(GameListState())
    val state: State<GameListState> = _state

//    TODO find a way to pass the list id
//    init {
//        getSingleList()
//    }
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

}