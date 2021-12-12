package com.naughtsmt.lintu.domain.repository

import com.naughtsmt.lintu.data.data_source.dto.ResponseDto
import com.naughtsmt.lintu.data.data_source.dto_access_token.AccessTokenDto
import com.naughtsmt.lintu.data.data_source.lists_dto.ListsDto
import com.naughtsmt.lintu.data.repository.model.Post

interface GameRepository {
    suspend fun getTopGameList(): ResponseDto

    suspend fun getGame(gameId: String): ResponseDto

    // TODO se a way to get the game list from a user
    suspend fun getUserGameList(): ResponseDto

    suspend fun getAccessToken(code: String): AccessTokenDto

    suspend fun getLists(): ListsDto

    suspend fun getSingleList(listId: String): ResponseDto
}