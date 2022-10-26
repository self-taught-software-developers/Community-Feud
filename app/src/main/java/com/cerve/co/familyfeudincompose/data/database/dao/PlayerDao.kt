package com.cerve.co.familyfeudincompose.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cerve.co.familyfeudincompose.data.database.entity.Player
import java.lang.annotation.RetentionPolicy

@Dao
interface PlayerDao {

    @Query("SELECT * FROM player")
    suspend fun getAll(): List<Player>

    @Insert
    suspend fun addAll(players: List<Player>)

}