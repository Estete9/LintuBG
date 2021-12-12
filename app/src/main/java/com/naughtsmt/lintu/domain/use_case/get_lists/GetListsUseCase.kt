package com.naughtsmt.lintu.domain.use_case.get_lists

import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.data.data_source.lists_dto.Lists
import com.naughtsmt.lintu.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetListsUseCase @Inject constructor(
    private val repository: GameRepository
) {
    operator fun invoke(/*here we need something*/): Flow<Resource<List<Lists>>> = flow {
        try {
            emit(Resource.Loading<List<Lists>>())
            val lists = repository.getLists().lists
            emit(Resource.Success<List<Lists>>(lists))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Lists>>(e.localizedMessage ?: "An Unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Lists>>("Couldn't reach server. Check your internet connection"))
        }
    }
}