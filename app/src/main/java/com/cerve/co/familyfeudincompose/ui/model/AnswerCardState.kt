package com.cerve.co.familyfeudincompose.ui.model

import androidx.compose.runtime.Immutable

@Immutable
data class AnswerCardState(
    val answer: String,
    val points: Int,
    val answered: Boolean = false
) {

    fun toAnswered() : AnswerCardState {
        return this.copy(answered = true)
    }

}
