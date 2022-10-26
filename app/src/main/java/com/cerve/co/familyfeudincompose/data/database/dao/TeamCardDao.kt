package com.cerve.co.familyfeudincompose.data.database.dao

import androidx.room.*
import com.cerve.co.familyfeudincompose.data.database.entity.TeamCard
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamCardDao {

    @Query("SELECT * FROM teamcard ORDER BY points DESC LIMIT 1")
    fun teamWithMostPoints(): Flow<TeamCard>

    @Query("SELECT * FROM teamcard WHERE name = :name")
    fun getByName(name: String?): Flow<TeamCard>

    @Query("SELECT * FROM teamcard")
    fun getAll(): Flow<List<TeamCard>>

    @Upsert
    suspend fun upsertTeam(team: TeamCard)

    @Query("UPDATE teamcard SET points = 0")
    suspend fun resetAllPoints()

}