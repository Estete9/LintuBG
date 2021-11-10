package com.naughtsmt.lintu.domain.use_case.get_game

import com.naughtsmt.lintu.common.Resource
import com.naughtsmt.lintu.data.data_source.dto.toGame
import com.naughtsmt.lintu.domain.model.Game
import com.naughtsmt.lintu.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    operator fun invoke(gameId: String): Flow<Resource<Game>> = flow {
        try {
            emit(Resource.Loading())
            val game = repository.getGame(gameId)
            emit(Resource.Success(game.toGame()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An Unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}