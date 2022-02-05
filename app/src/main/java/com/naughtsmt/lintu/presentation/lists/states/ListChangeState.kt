package com.naughtsmt.lintu.presentation.lists.states

import com.naughtsmt.lintu.data.data_source.remote.new_list_dto.List

data class ListChangeState(
    val isLoading: Boolean = false,
    val newList: List? = null,
    val error: String = ""
)
