package com.naughtsmt.lintu.presentation.game_list

data class DeleteGameFromListState(
    val isLoading: Boolean = false,
    val operationResult: Boolean = false,
    val error: String = "",
)