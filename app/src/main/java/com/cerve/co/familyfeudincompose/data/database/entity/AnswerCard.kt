package com.cerve.co.familyfeudincompose.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AnswerCard(
    @PrimaryKey val answer: String,
    val points: Int,
    val question: String
)
