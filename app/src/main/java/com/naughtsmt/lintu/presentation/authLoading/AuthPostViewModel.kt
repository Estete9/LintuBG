package com.naughtsmt.lintu.presentation.authLoading

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naughtsmt.lintu.common.AuthData
import com.naughtsmt.lintu.common.Constants
import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.domain.use_case.authPost.AuthPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthPostViewModel @Inject constructor(
    private val authPostUseCase: AuthPostUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AuthPostState())
    val state: State<AuthPostState> = _state
    private val tag = "AUTH_POST_VIEW_MODEL"

    init {
        savedStateHandle.get<String>(Constants.PARAM_CODE)?.let { code ->
            Log.d(tag, "the code in the VM is: $code")
            getAuthToken(code)
        }
    }

    fun getAuthToken(code: String) {
        authPostUseCase(code = code).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AuthPostState(accessToken = result.data)
                    AuthData.authToken.value = state.value.accessToken
                }
                is Resource.Loading -> {
                    _state.value = AuthPostState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value =
                        AuthPostState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

}