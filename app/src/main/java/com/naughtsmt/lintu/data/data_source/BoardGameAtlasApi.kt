package com.naughtsmt.lintu.data.data_source

import com.naughtsmt.lintu.data.data_source.dto.GameListResponse
import retrofit2.http.GET

interface BoardGameAtlasApi {
    @GET("/search")
    suspend fun getGameList(): GameListResponse
}