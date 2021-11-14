package com.naughtsmt.lintu.data.data_source

import com.naughtsmt.lintu.data.data_source.dto.GameDto
import retrofit2.http.GET
import retrofit2.http.Path

interface BoardGameAtlasApi {

    //TODO for future this returns the registered user's game list
    @GET("/search")
    suspend fun getUserGameList(): List<GameDto>

    @GET("/search/{gameName}")
    suspend fun getGameById(@Path("gameName") gameId: String): GameDto

    @GET("/search")
    suspend fun getTopGamesList(): List<GameDto>
}