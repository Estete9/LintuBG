package com.naughtsmt.lintu.presentation.scaffold.states

data class DeleteGameFromListState(
    val isLoading: Boolean = false,
    val operationResult: Boolean = false,
    val error: String = "",
)