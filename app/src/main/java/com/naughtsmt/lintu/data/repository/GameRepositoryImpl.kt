package com.naughtsmt.lintu.data.repository

//import com.naughtsmt.lintu.data.data_source.GamesDao
import com.naughtsmt.lintu.data.data_source.BoardGameAtlasApi
import com.naughtsmt.lintu.data.data_source.dto.ResponseDto
import com.naughtsmt.lintu.data.data_source.dto_access_token.AccessTokenDto
import com.naughtsmt.lintu.data.data_source.lists_dto.ListsDto
import com.naughtsmt.lintu.data.data_source.lists_dto.SuccessDto
import com.naughtsmt.lintu.data.data_source.new_list_dto.NewListDto
import com.naughtsmt.lintu.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api: BoardGameAtlasApi,
//    private val dao: GamesDao

) : GameRepository {
    override suspend fun getTopGameList(): ResponseDto {
        return api.getTopGamesList()
    }

    override suspend fun getGameById(gameId: String): ResponseDto {
        return api.getGameById(gameId)
    }

    override suspend fun getUserGameList(): ResponseDto {
        return api.getUsersGameList()
    }

    override suspend fun getAccessToken(params: HashMap<String, String>): AccessTokenDto {
        return api.getAccessToken(params)
    }

    override suspend fun getListsFromApi(): ListsDto {
        return api.getLists()
    }

    override suspend fun getSingleListFromApi(listId: String): ResponseDto {
        return api.getSingleList(listId)
    }

    override suspend fun createListIntoApi(authToken: String, name: String): NewListDto {
        return api.createList(authToken, name)
    }

    override suspend fun deleteListFromApi(authToken: String, list_id: String): SuccessDto {
        return api.deleteList(authToken, list_id)
    }

    override suspend fun addGameToList(
        authToken: String,
        listId: String,
        gameId: String
    ): SuccessDto {
        return api.addGameToList(authToken, listId, gameId)
    }

    override suspend fun getGameByName(name: String): ResponseDto {
        return api.getGameByName(name)
    }

//    override fun getListsFromDB(): Flow<List<Game>> {
//        return dao.getList()
//    }
//
//    override suspend fun getGameByIdFromDB(idKey: Int): Game? {
//        return dao.getGame(idKey)
//    }
//
//    override suspend fun insertGameIntoDB(game: Game) {
//        dao.insertGame(game)
//    }
//
//    override suspend fun deleteGameFromDB(game: Game) {
//        dao.deleteGame(game)
//    }
}