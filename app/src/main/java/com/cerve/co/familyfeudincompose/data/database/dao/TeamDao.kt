package com.cerve.co.familyfeudincompose.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.cerve.co.familyfeudincompose.data.database.entity.Team

@Dao
interface TeamDao {

    @Query("SELECT * FROM team")
    suspend fun getAll(): List<Team>

}