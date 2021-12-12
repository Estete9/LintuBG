package com.naughtsmt.lintu.presentation.users_game_list

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.domain.use_case.get_user_game_list.GetUserGameListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UsersGameListViewModel @Inject constructor(
    //TODO make a usersGameListUseCase
    private val getUserGameListUseCase: GetUserGameListUseCase,
) : ViewModel(), DefaultLifecycleObserver {

    private val _state = mutableStateOf(UsersGameListState())
    val state: State<UsersGameListState> = _state


    fun getAuthToken(code: String?, context: Context) {

//TODO make a usersGameListUseCase
        /*here*/getUserGameListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UsersGameListState(games = result.data ?: listOf())
                }
                is Resource.Loading -> {
                    _state.value =
                        UsersGameListState(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Error -> {
                    _state.value = UsersGameListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}