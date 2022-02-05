package com.naughtsmt.lintu.presentation.lists.states

import com.naughtsmt.lintu.data.data_source.remote.lists_dto.Lists

data class ListsState(
    val isLoading: Boolean = false,
    val lists: List<Lists> = emptyList(),
    val error: String = ""
)
