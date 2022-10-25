package com.cerve.co.familyfeudincompose.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.cerve.co.familyfeudincompose.data.database.entity.AnswerCard

@Dao
interface AnswerCardDao {

    @Query("SELECT * FROM answercard")
    suspend fun getAll(): List<AnswerCard>

}