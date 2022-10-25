package com.cerve.co.familyfeudincompose.data

import com.cerve.co.familyfeudincompose.data.database.dao.AnswerCardDao
import javax.inject.Inject

class FamilyFeudRepository @Inject constructor(
    private val answerCardDao: AnswerCardDao
) {

    suspend fun getQuestionCards() {
        val questionCards = answerCardDao.getAll()
            .groupBy { it.question }

    }

}