package com.cerve.co.familyfeudincompose.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.cerve.co.familyfeudincompose.data.database.entity.Player

@Dao
interface PlayerDao {

    @Query("SELECT * FROM player")
    suspend fun getAll(): List<Player>

}