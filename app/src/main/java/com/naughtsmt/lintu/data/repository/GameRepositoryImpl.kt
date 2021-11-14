package com.naughtsmt.lintu.data.repository

import com.naughtsmt.lintu.data.data_source.BoardGameAtlasApi
import com.naughtsmt.lintu.data.data_source.dto.GameDto
import com.naughtsmt.lintu.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api: BoardGameAtlasApi
): GameRepository {
    override suspend fun getGame(gameId: String): GameDto {
    return api.getGameById(gameId)
    }

    override suspend fun getTopGameList(): List<GameDto> {
        return api.getTopGamesList()
    }

    override suspend fun getUserGameList(): List<GameDto> {
        return api.getUserGameList()
    }
}