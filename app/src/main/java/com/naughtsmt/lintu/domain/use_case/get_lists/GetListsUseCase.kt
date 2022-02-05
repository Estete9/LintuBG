package com.naughtsmt.lintu.domain.use_case.get_lists

import android.util.Log
import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.data.data_source.remote.lists_dto.Lists
import com.naughtsmt.lintu.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

val tag = "GetListsUseCase"

class GetListsUseCase @Inject constructor(
    private val repository: GameRepository
) {
    operator fun invoke(): Flow<Resource<List<Lists>>> = flow {
        try {
            emit(Resource.Loading<List<Lists>>())
            val lists = repository.getListsFromApi().lists
            Log.d(tag, "getLists from api called")
            emit(Resource.Success<List<Lists>>(lists))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Lists>>(e.localizedMessage ?: "An Unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Lists>>("Couldn't reach server. Check your internet connection"))
        }
    }
}