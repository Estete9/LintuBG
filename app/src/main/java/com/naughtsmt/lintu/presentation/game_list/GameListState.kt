package com.naughtsmt.lintu.presentation.game_list

import com.naughtsmt.lintu.data.repository.model.Game

data class GameListState(
    val isLoading: Boolean = false,
    val games: List<Game> = emptyList(),
    val error: String = ""
)
