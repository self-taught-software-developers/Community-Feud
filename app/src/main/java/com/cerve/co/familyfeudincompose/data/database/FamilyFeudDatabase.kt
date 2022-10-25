package com.cerve.co.familyfeudincompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cerve.co.familyfeudincompose.data.database.dao.AnswerCardDao
import com.cerve.co.familyfeudincompose.data.database.dao.PlayerDao
import com.cerve.co.familyfeudincompose.data.database.dao.TeamDao
import com.cerve.co.familyfeudincompose.data.database.entity.AnswerCard
import com.cerve.co.familyfeudincompose.data.database.entity.Player
import com.cerve.co.familyfeudincompose.data.database.entity.Team

@Database(
    entities = [AnswerCard::class, Player::class, Team::class],
    version = 1,
    exportSchema = false
)
abstract class FamilyFeudDatabase : RoomDatabase() {
    abstract fun answerCardDao(): AnswerCardDao
    abstract fun playerDao(): PlayerDao
    abstract fun teamDao(): TeamDao
}