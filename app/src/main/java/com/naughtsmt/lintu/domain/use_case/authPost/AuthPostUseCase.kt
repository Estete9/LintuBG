package com.naughtsmt.lintu.domain.use_case.authPost

import android.util.Log
import com.naughtsmt.lintu.common.Constants.CLIENT_ID
import com.naughtsmt.lintu.common.Constants.CLIENT_SECRET
import com.naughtsmt.lintu.common.Constants.REDIRECT_URI
import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.data.data_source.remote.dto_access_token.AccessTokenDto
import com.naughtsmt.lintu.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

val tag = "AuthUseCase"

class AuthPostUseCase @Inject constructor(
    private val repository: GameRepository,
) {
    operator fun invoke(code: String): Flow<Resource<AccessTokenDto>> = flow {
        try {
            emit(Resource.Loading<AccessTokenDto>())

            val params = HashMap<String, String>()
            params["client_id"] = CLIENT_ID
            params["client_secret"] = CLIENT_SECRET
            params["redirect_uri"] = REDIRECT_URI
            params["grant_type"] = "authorization_code"
            params["code"] = code

            val accessToken = repository.getAccessToken(params)
            Log.d(tag, "access token success: $accessToken")
            emit(Resource.Success<AccessTokenDto>(accessToken))
        } catch (e: HttpException) {
            emit(
                Resource.Error<AccessTokenDto>(
                    e.localizedMessage ?: "An Unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<AccessTokenDto>("Couldn't reach server. Check your internet connection"))
        }
    }
}