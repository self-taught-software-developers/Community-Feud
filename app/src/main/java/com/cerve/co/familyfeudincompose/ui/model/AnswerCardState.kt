package com.cerve.co.familyfeudincompose.ui.model

import android.system.StructStat
import androidx.compose.runtime.Immutable

@Immutable
data class AnswerCardState(
    val answer: String,
    val points: Int,
    val answered: Boolean
)
