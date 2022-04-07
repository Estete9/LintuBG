package com.naughtsmt.lintu.data.data_source

import com.naughtsmt.lintu.common.Constants.CLIENT_ID
import com.naughtsmt.lintu.common.Constants.USERNAME
import com.naughtsmt.lintu.data.data_source.remote.dto.ResponseDto
import com.naughtsmt.lintu.data.data_source.remote.dto_access_token.AccessTokenDto
import com.naughtsmt.lintu.data.data_source.remote.lists_dto.ListsDto
import com.naughtsmt.lintu.data.data_source.remote.lists_dto.SuccessDto
import com.naughtsmt.lintu.data.data_source.remote.new_list_dto.NewListDto
import retrofit2.http.*

interface BoardGameAtlasApi {

    @GET("api/search")
    suspend fun getGameById(
        @Query("ids")
        ids: String,
        @Query("client_id")
        client_id: String = CLIENT_ID
    ): ResponseDto

    @GET("api/search")
    suspend fun getTopGamesList(
//        @Query("skip")
//        skip: Int,
        @Query("limit")
        limit: Int = 10,
        @Query("client_id")
        client_id: String = CLIENT_ID
    ): ResponseDto

    @GET("api/search")
    suspend fun getSingleList(
        @Query("list_id")
        list_id: String,
        @Query("client_id")
        client_id: String = CLIENT_ID
    ): ResponseDto

    @GET("api/lists")
    suspend fun getLists(
        @Query("username")
        username: String = USERNAME,
        @Query("client_id")
        client_id: String = CLIENT_ID
    ): ListsDto

    @GET("api/search")
    suspend fun getGameByName(
        @Query("name")
        name: String,
        @Query("fuzzy_match")
        fuzzy_match: Boolean = true,
        @Query("limit")
        limit: Int = 10,
        @Query("client_id")
        client_id: String = CLIENT_ID
    ): ResponseDto

    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun getAccessToken(
        @FieldMap params: HashMap<String, String>
    ): AccessTokenDto

    @FormUrlEncoded
    @POST("api/lists")
    suspend fun createList(
        @Header("Authorization") auth_token: String,
        @Field("name") name: String,
        @Query("client_id")
        client_id: String = CLIENT_ID
    ): NewListDto

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "api/lists", hasBody = true)
    suspend fun deleteList(
        @Header("Authorization") auth_token: String,
        @Field("list_id")
        list_id: String,
        @Query("client_id")
        client_id: String = CLIENT_ID
    ): SuccessDto


    @FormUrlEncoded
    @POST("api/lists/add")
    suspend fun addGameToList(
        @Header("Authorization") auth_token: String,
        @Field("list_id") list_id: String,
        @Field("game_id") game_id: String,
        @Query("client_id")
        client_id: String = CLIENT_ID

    ): SuccessDto

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "api/lists/item", hasBody = true)
    suspend fun deleteGameFromList(
        @Header("Authorization") auth_token: String,
        @Field("list_id")
        list_id: String,
        @Field("game_id")
        game_id: String,
        @Query("client_id")
        client_id: String = CLIENT_ID
    ): SuccessDto
}