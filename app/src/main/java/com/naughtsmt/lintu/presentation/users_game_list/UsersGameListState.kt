package com.naughtsmt.lintu.presentation.users_game_list

import com.naughtsmt.lintu.data.repository.model.Game

data class UsersGameListState(
    val isLoading: Boolean = false,
    val games: List<Game> = emptyList(),
    val error: String = ""
)
