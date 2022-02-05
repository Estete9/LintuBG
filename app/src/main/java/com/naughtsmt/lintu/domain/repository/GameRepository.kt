package com.naughtsmt.lintu.domain.repository

import com.naughtsmt.lintu.data.data_source.remote.dto.ResponseDto
import com.naughtsmt.lintu.data.data_source.remote.dto_access_token.AccessTokenDto
import com.naughtsmt.lintu.data.data_source.remote.lists_dto.ListsDto
import com.naughtsmt.lintu.data.data_source.remote.lists_dto.SuccessDto
import com.naughtsmt.lintu.data.data_source.remote.new_list_dto.NewListDto

interface GameRepository {
    suspend fun getTopGameList(): ResponseDto

    suspend fun getGameById(gameId: String): ResponseDto


    suspend fun getAccessToken(params: HashMap<String, String>): AccessTokenDto

    suspend fun getListsFromApi(): ListsDto

    suspend fun getSingleListFromApi(listId: String): ResponseDto

    suspend fun createListIntoApi(
        authToken: String,
        name: String
    ): NewListDto

    suspend fun deleteListFromApi(
        authToken: String,
        listId: String
    ): SuccessDto

    suspend fun addGameToListInApi(
        authToken: String,
        listId: String,
        gameId: String
    ): SuccessDto

    suspend fun getGameByNameFromApi(
        name: String
    ): ResponseDto

    suspend fun deleteGameFromListApi(
        authToken: String,
        listId: String,
        gameId: String
    ): SuccessDto
//    fun getListsFromDB(): Flow<List<Game>>
//
//    suspend fun getGameByIdFromDB(idKey: Int): Game?
//
//    suspend fun insertGameIntoDB(game: Game)
//
//    suspend fun deleteGameFromDB(game: Game)
}