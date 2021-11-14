package com.naughtsmt.lintu.domain.repository

import com.naughtsmt.lintu.data.data_source.dto.GameDto

interface GameRepository {
    suspend fun getGame(gameId: String): GameDto

    suspend fun getTopGameList(): List<GameDto>

    // TODO se a way to get the game list from a user
    suspend fun getUserGameList(): List<GameDto>

}