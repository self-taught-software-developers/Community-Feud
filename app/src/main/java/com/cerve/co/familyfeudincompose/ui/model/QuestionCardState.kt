package com.cerve.co.familyfeudincompose.ui.model

import androidx.compose.runtime.Immutable

@Immutable
data class QuestionCardState(
    val question: String,
    val answerCards: List<AnswerCardState>
)
