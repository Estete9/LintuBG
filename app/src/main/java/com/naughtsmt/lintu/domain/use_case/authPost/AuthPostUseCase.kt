package com.naughtsmt.lintu.domain.use_case.authPost

import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.data.data_source.dto_access_token.AccessTokenDto
import com.naughtsmt.lintu.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthPostUseCase @Inject constructor(
    private val repository: GameRepository,
) {
    operator fun invoke(code: String): Flow<Resource<AccessTokenDto>> = flow {
        try {
            emit(Resource.Loading<AccessTokenDto>())
//            val params = HashMap<String, String>()
//            params["client_id"] = CLIENT_ID
//            params["client_secret"] = CLIENT_SECRET
//            params["redirect_uri"] = REDIRECT_URI
//            params["grant_type"] = "authorization_code"
//            params["code"] = code
            val accessToken = repository.getAccessToken(code = code)
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