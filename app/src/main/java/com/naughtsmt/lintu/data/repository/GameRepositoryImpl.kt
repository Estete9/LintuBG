package com.naughtsmt.lintu.data.repository

import com.naughtsmt.lintu.data.data_source.BoardGameAtlasApi
import com.naughtsmt.lintu.data.data_source.dto.ResponseDto
import com.naughtsmt.lintu.data.data_source.dtoOld.GameDto
import com.naughtsmt.lintu.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api: BoardGameAtlasApi
): GameRepository {
    override suspend fun getTopGameList(): ResponseDto {
        return api.getTopGamesList()
    }

    override suspend fun getGame(gameId: String): ResponseDto {
    return api.getGameById(gameId)
    }

    override suspend fun getUserGameList(): List<GameDto> {
        return api.getUserGameList()
    }
}