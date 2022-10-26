package com.cerve.co.familyfeudincompose.data

import com.cerve.co.familyfeudincompose.data.database.dao.AnswerCardDao
import com.cerve.co.familyfeudincompose.data.database.dao.PlayerDao
import com.cerve.co.familyfeudincompose.data.database.dao.TeamDao
import com.cerve.co.familyfeudincompose.data.database.entity.Team
import javax.inject.Inject

class FamilyFeudRepository @Inject constructor(
    private val answerCardDao: AnswerCardDao,
    private val teamDao: TeamDao,
    private val playerDao: PlayerDao
) {

    suspend fun getQuestionCards() = answerCardDao.getAll()
        .groupBy { it.question }

    fun fetchAllTeams() = teamDao.getAll()
    fun fetchTeamWithMostPoints() = teamDao.teamWithMostPoints()
    fun fetchTeam(name: String?) = teamDao.getByName(name)

    suspend fun createTeam(name: String, turns: Int) {
        teamDao.upsertTeam(
            Team(
                name = name,
                points = 0,
                turns = turns
            )
        )
    }

    suspend fun updatePoints(team: Team) {
        teamDao.upsertTeam(team)
    }

}