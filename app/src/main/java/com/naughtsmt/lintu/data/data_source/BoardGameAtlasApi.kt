package com.naughtsmt.lintu.data.data_source

import com.naughtsmt.lintu.common.Constants.CLIENT_ID
import com.naughtsmt.lintu.data.data_source.dto.ResponseDto
import com.naughtsmt.lintu.data.data_source.dto_access_token.AccessTokenDto
import com.naughtsmt.lintu.data.data_source.lists_dto.ListsDto
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
//    @FormUrlEncoded
////    @Headers("content-type: application/x-www-form-urlencoded")
//    @POST("oauth/token")
//    suspend fun getAccessToken(
//    ): AccessTokenDto

    @GET("api/lists")
    suspend fun getLists(
        @Query("username")
        username: String = "naughtSMT",
        @Query("client_id")
        client_id: String = CLIENT_ID
    ): ListsDto

    @GET("api/search")
    suspend fun getSingleList(
        @Query("list_id")
        list_id: String,
        client_id: String = CLIENT_ID
    ): ResponseDto
}