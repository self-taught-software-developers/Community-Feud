package com.cerve.co.familyfeudincompose.data.database.dao

import androidx.room.*
import com.cerve.co.familyfeudincompose.data.database.entity.Team
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    @Query("SELECT * FROM team ORDER BY points DESC LIMIT 1")
    fun teamWithMostPoints(): Flow<Team>

    @Query("SELECT * FROM team WHERE name = :name")
    fun getByName(name: String?): Flow<Team>

    @Query("SELECT * FROM team")
    fun getAll(): Flow<List<Team>>

    @Upsert
    suspend fun upsertTeam(team: Team)

}