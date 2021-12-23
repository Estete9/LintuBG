package com.naughtsmt.lintu.domain.use_case.create_list

import com.naughtsmt.lintu.common.AuthData.authToken
import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.data.data_source.new_list_dto.NewListDto
import com.naughtsmt.lintu.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class CreateListUseCase @Inject constructor(
    private val repository: GameRepository
) {
    operator fun invoke(name: String): Flow<Resource<NewListDto>> = flow {
        try {
            emit(Resource.Loading<NewListDto>())
            val newList =
                repository.createListIntoApi("Bearer ${authToken.value?.access_token.toString()}", name)
            emit(Resource.Success<NewListDto>(newList))
        } catch (e: HttpException) {
            emit(Resource.Error<NewListDto>(e.localizedMessage ?: "An Unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<NewListDto>("Couldn't reach server. Check your internet connection"))
        }
    }
}

