package com.naughtsmt.lintu.data.repository

import com.naughtsmt.lintu.data.data_source.BoardGameAtlasApi
import com.naughtsmt.lintu.data.data_source.dto.ResponseDto
import com.naughtsmt.lintu.data.data_source.dto_access_token.AccessTokenDto
import com.naughtsmt.lintu.data.data_source.lists_dto.ListsDto
import com.naughtsmt.lintu.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api: BoardGameAtlasApi
) : GameRepository {
    override suspend fun getTopGameList(): ResponseDto {
        return api.getTopGamesList()
    }

    override suspend fun getGame(gameId: String): ResponseDto {
        return api.getGameById(gameId)
    }

    override suspend fun getUserGameList(): ResponseDto {
        return api.getUsersGameList()
    }

    override suspend fun getAccessToken(params: HashMap<String, String>): AccessTokenDto {
        return api.getAccessToken(params)
    }

    override suspend fun getLists(): ListsDto {
        return api.getLists()
    }

    override suspend fun getSingleList(listId: String): ResponseDto {
        return api.getSingleList(listId)
    }
}