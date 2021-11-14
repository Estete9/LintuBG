package com.naughtsmt.lintu.presentation.game_detail

import com.naughtsmt.lintu.domain.model.Game

data class GameDetailState(
    val isLoading: Boolean = false,
    val game: Game? = null,
    val error: String = ""
)
