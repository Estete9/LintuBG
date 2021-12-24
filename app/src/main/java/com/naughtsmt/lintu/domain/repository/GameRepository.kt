package com.naughtsmt.lintu.domain.repository

import com.naughtsmt.lintu.data.data_source.dto.ResponseDto
import com.naughtsmt.lintu.data.data_source.dto_access_token.AccessTokenDto
import com.naughtsmt.lintu.data.data_source.lists_dto.ListsDto
import com.naughtsmt.lintu.data.data_source.lists_dto.SuccessDto
import com.naughtsmt.lintu.data.data_source.new_list_dto.NewListDto

interface GameRepository {
    suspend fun getTopGameList(): ResponseDto

    suspend fun getGameById(gameId: String): ResponseDto

    suspend fun getUserGameList(): ResponseDto

    suspend fun getAccessToken(params: HashMap<String, String>): AccessTokenDto

    suspend fun getListsFromApi(): ListsDto

    suspend fun getSingleListFromApi(listId: String): ResponseDto

    suspend fun createListIntoApi(
        authToken: String,
        name: String
    ): NewListDto

    suspend fun deleteListFromApi(
        authToken: String,
        list_id: String
    ): SuccessDto

    suspend fun addGameToList(
        authToken: String,
        listId: String,
        gameId: String
    ): SuccessDto

    suspend fun getGameByName(
        name: String
    ): ResponseDto
//    fun getListsFromDB(): Flow<List<Game>>
//
//    suspend fun getGameByIdFromDB(idKey: Int): Game?
//
//    suspend fun insertGameIntoDB(game: Game)
//
//    suspend fun deleteGameFromDB(game: Game)
}