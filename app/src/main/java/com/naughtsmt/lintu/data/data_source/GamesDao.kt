package com.naughtsmt.lintu.data.data_source

import androidx.room.*
import com.naughtsmt.lintu.data.repository.model.Game
import kotlinx.coroutines.flow.Flow

//@Dao
//interface GamesDao {
//    @Query("SELECT * FROM game")
//    fun getList(): Flow<List<Game>>
//
//    @Query("SELECT * FROM game WHERE idKey=:idKey")
//    suspend fun getGame(idKey: Int): Game?
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertGame(game: Game)
//
//    @Delete
//    suspend fun deleteGame(game: Game)
//}