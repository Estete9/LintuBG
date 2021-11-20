package com.naughtsmt.lintu.data.data_source

import com.naughtsmt.lintu.common.Constants
import com.naughtsmt.lintu.data.data_source.dto.ResponseDto
import com.naughtsmt.lintu.data.data_source.dtoOld.GameDto
import retrofit2.http.GET
import retrofit2.http.Query

interface BoardGameAtlasApi {

    //TODO for future this returns the registered user's game list (must change the returning type)
    @GET("search")
    suspend fun getUserGameList(): List<GameDto>

    @GET("search")
    suspend fun getGameById(
//        @Path("gameName") gameId: String,
        @Query("ids")
        ids: String,
        @Query("client_id")
        client_id: String = Constants.CLIENT_ID
    ): ResponseDto

    @GET("search")
    suspend fun getTopGamesList(
        @Query("client_id")
        client_id: String = Constants.CLIENT_ID
    ): ResponseDto
}