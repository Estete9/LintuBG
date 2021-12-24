package com.naughtsmt.lintu.data.data_source

import com.naughtsmt.lintu.common.Constants.CLIENT_ID
import com.naughtsmt.lintu.common.Constants.USERNAME
import com.naughtsmt.lintu.data.data_source.dto.ResponseDto
import com.naughtsmt.lintu.data.data_source.dto_access_token.AccessTokenDto
import com.naughtsmt.lintu.data.data_source.lists_dto.ListsDto
import com.naughtsmt.lintu.data.data_source.lists_dto.SuccessDto
import com.naughtsmt.lintu.data.data_source.new_list_dto.NewListDto
import retrofit2.http.*

interface BoardGameAtlasApi {
    //TODO for future this returns the registered user's game list (must change the returning type)
    @GET("api/search")
    suspend fun getUsersGameList(
//        TODO
    ): ResponseDto

    @GET("api/search")
    suspend fun getGameById(
        @Query("ids")
        ids: String,
        @Query("client_id")
        client_id: String = CLIENT_ID
    ): ResponseDto

    @GET("api/search")
    suspend fun getTopGamesList(
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
    suspend fun getSingleList(
        @Query("list_id")
        list_id: String,
        @Query("client_id")
        client_id: String = CLIENT_ID
    ): ResponseDto

    @GET("api/search")
    suspend fun getGameByName(
        @Query("name")
        name: String,
        @Query("client_id")
        client_id: String = CLIENT_ID
    ): ResponseDto

    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun getAccessToken(
//        @Field("client_id") client_id: String = CLIENT_ID,
//        @Field("client_secret") client_secret: String = Constants.CLIENT_SECRET,
//        @Field("code") code: String,
//        @Field("redirect_uri") redirect_uri: String = Constants.REDIRECT_URI,
//        @Field("grant_type") grant_type: String = "authorization_code",
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
}