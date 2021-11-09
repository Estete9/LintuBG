package com.naughtsmt.lintu.domain.model

data class GameListResponse(
    val count: Int,
    val games: List<Game>
)