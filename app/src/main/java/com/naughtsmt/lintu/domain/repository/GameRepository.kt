package com.naughtsmt.lintu.domain.repository

import com.naughtsmt.lintu.data.data_source.dto.ResponseDto
import com.naughtsmt.lintu.data.data_source.dtoOld.GameDto

interface GameRepository {
    suspend fun getTopGameList(): ResponseDto

    suspend fun getGame(gameId: String): ResponseDto

    // TODO se a way to get the game list from a user
    suspend fun getUserGameList(): List<GameDto>

}