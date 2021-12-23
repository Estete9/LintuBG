package com.naughtsmt.lintu.domain.use_case.get_single_list

import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.data.data_source.dto.toGame
import com.naughtsmt.lintu.data.repository.model.Game
import com.naughtsmt.lintu.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSingleListUseCase @Inject constructor(
    private val repository: GameRepository
) {
    operator fun invoke(listId: String): Flow<Resource<List<Game>>> = flow {
        try {
            emit(Resource.Loading<List<Game>>())
            val singleList = repository.getSingleListFromApi(listId).games.map { it.toGame() }
            emit(Resource.Success<List<Game>>(singleList))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Game>>(e.localizedMessage ?: "An Unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Game>>("Couldn't reach server. Check your internet connection"))
        }
    }
}