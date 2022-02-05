package com.naughtsmt.lintu.domain.use_case.delete_game_from_list

import com.naughtsmt.lintu.common.AuthData
import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.data.data_source.remote.lists_dto.SuccessDto
import com.naughtsmt.lintu.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DeleteGameFromListUseCase @Inject constructor(
    val repository: GameRepository
) {
    operator fun invoke(listId: String, gameId: String): Flow<Resource<SuccessDto>> = flow {
        try {
            emit(Resource.Loading<SuccessDto>())
            val response = repository.deleteGameFromListApi(
                "Bearer ${AuthData.authToken.value?.access_token.toString()}",
                listId,
                gameId
            )
            emit(Resource.Success<SuccessDto>(response))
        } catch (e: HttpException) {
            emit(
                Resource.Error<SuccessDto>(
                    e.localizedMessage ?: "An Unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<SuccessDto>("Couldn't reach server. Check your internet connection"))
        }

    }
}