package com.naughtsmt.lintu.presentation.game_detail

data class AddGameToListState(
    val isLoading: Boolean = false,
    val operationResult: Boolean = false,
    val error: String = "",
)