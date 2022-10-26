package com.cerve.co.familyfeudincompose.data

import com.cerve.co.familyfeudincompose.data.database.dao.AnswerCardDao
import com.cerve.co.familyfeudincompose.data.database.dao.TeamCardDao
import com.cerve.co.familyfeudincompose.data.database.entity.TeamCard
import javax.inject.Inject

class FamilyFeudRepository @Inject constructor(
    private val answerCardDao: AnswerCardDao,
    private val teamDao: TeamCardDao
) {

    suspend fun getQuestionCards() = answerCardDao.getAll()
        .groupBy { it.question }

    suspend fun deleteTeam(team: TeamCard) = teamDao.delete(team)

    fun fetchAllTeams() = teamDao.getAll()
    fun fetchTeamWithMostPoints() = teamDao.teamWithMostPoints()
    fun fetchTeam(name: String?) = teamDao.getByName(name)

    suspend fun createTeam(name: String, turns: Int) {
        teamDao.upsertTeam(
            TeamCard(
                name = name,
                points = 0,
                turns = turns
            )
        )
    }

    suspend fun updatePoints(team: TeamCard) {
        teamDao.upsertTeam(team)
    }

    suspend fun resetAllPoints() {
        teamDao.resetAllPoints()
    }

}