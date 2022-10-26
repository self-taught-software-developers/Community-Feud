package com.cerve.co.familyfeudincompose.ui

import android.provider.SyncStateContract.Helpers.update
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.co.familyfeudincompose.data.FamilyFeudRepository
import com.cerve.co.familyfeudincompose.data.database.entity.TeamCard
import com.cerve.co.familyfeudincompose.ui.model.AnswerCardState
import com.cerve.co.familyfeudincompose.ui.model.QuestionCardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FamilyFeudViewModel @Inject constructor(
    private val repository: FamilyFeudRepository
) : ViewModel() {

    val questions = mutableStateListOf<QuestionCardState>()
    private val _currentQuestion = MutableStateFlow<QuestionCardState?>(null)
    val currentQuestion = _currentQuestion.asStateFlow()

    private val hasGone = mutableStateListOf<String>()

    val createdTeams = repository.fetchAllTeams()
        .stateIn(viewModelScope, WhileSubscribed(), emptyList())

    private val _teamNowPlaying = MutableStateFlow<TeamCard?>(null)
    val teamNowPlaying = _teamNowPlaying.flatMapMerge {
        repository.fetchTeam(it?.name)
    }.stateIn(viewModelScope, WhileSubscribed(), null)

    private val _strikeAccumulation = MutableStateFlow(0)
    val strikeAccumulation = _strikeAccumulation.asStateFlow()

    val winningTeam = repository.fetchTeamWithMostPoints()
        .stateIn(viewModelScope, WhileSubscribed(), null)

    init {
        viewModelScope.launch {
            repository.getQuestionCards().forEach { (question, answerCardList) ->
                QuestionCardState(
                    question = question,
                    answerCards = answerCardList.map { card ->
                        AnswerCardState(
                            answer = card.answer,
                            points = card.points
                        )
                    }
                ).also { questions.add(it) }
            }
        }
    }

    fun nextTeam(updateStrikes: Boolean = false) = viewModelScope.launch {
        createdTeams.value.filterNot { it.name in hasGone }.ifEmpty {
            hasGone.clear()
            createdTeams.value
        }.random().also {
            hasGone.add(it.name)
            if (updateStrikes) {
                _strikeAccumulation.update { 0 }
            }
            _teamNowPlaying.emit(it)
        }
    }

    fun selectQuestion(index: Int) = viewModelScope.launch {
        _currentQuestion.update { questions[index] }
        questions.removeAt(index)
    }

    fun addStrike() {

        _strikeAccumulation.updateAndGet {
            if (it >= 3) { 0 } else { it + 1 }
        }.also { if (it == 0) nextTeam() }
    }

    fun createNewTeam(name: String, playerCount: Int) = viewModelScope.launch {
        repository.createTeam(name, playerCount)
    }

    fun awardPoints(points: Int, team: TeamCard) = viewModelScope.launch {

        val updated = team.copy(points = team.points + points)
        repository.updatePoints(updated)

    }

    fun deleteTeamAtIndex(index: Int) = viewModelScope.launch {
        repository.deleteTeam(createdTeams.value[index])
    }

    fun resetPoints() = viewModelScope.launch {
        hasGone.clear()
        _strikeAccumulation.update { 0 }
        repository.resetAllPoints()
    }

}