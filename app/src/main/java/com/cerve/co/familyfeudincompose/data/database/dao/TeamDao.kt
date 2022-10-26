package com.cerve.co.familyfeudincompose.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cerve.co.familyfeudincompose.data.database.entity.Team
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    @Query("SELECT * FROM team ORDER BY points ASC LIMIT 1")
    fun teamWithMostPoints(): Flow<Team>

    @Query("SELECT * FROM team WHERE name = :name")
    fun getByName(name: String?): Flow<Team>

    @Insert
    suspend fun addTeam(team: Team)

    @Update
    suspend fun updateTeam(team: Team)

}