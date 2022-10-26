package com.cerve.co.familyfeudincompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cerve.co.familyfeudincompose.data.database.dao.AnswerCardDao
import com.cerve.co.familyfeudincompose.data.database.dao.TeamCardDao
import com.cerve.co.familyfeudincompose.data.database.entity.AnswerCard
import com.cerve.co.familyfeudincompose.data.database.entity.TeamCard

@Database(
    entities = [AnswerCard::class, TeamCard::class],
    version = 1,
    exportSchema = false
)
abstract class FamilyFeudDatabase : RoomDatabase() {
    abstract fun answerCardDao(): AnswerCardDao
    abstract fun teamDao(): TeamCardDao
}