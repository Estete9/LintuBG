package com.naughtsmt.lintu.presentation.scaffold

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.naughtsmt.lintu.presentation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(ScaffoldState())
    val state: State<ScaffoldState> = _state
    init {
        setCurrentScreen(state.value.currentScreen)
    }
    fun setCurrentScreen(screen: Screens) {
        _state.value = ScaffoldState(currentScreen = screen)
    }
}