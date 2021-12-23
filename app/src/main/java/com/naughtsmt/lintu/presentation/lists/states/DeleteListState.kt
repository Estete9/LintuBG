package com.naughtsmt.lintu.presentation.lists.states

data class DeleteListState(
    val isLoading: Boolean = false,
    val operationResult: Boolean = false,
    val error: String = "",
)
