package com.naughtsmt.lintu.presentation.lists.components

import androidx.compose.runtime.mutableStateOf
import com.naughtsmt.lintu.data.data_source.remote.lists_dto.Lists

object Lists {
    val lists = mutableStateOf<List<Lists>>(emptyList())
}