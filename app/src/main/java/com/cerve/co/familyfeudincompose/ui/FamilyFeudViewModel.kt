package com.cerve.co.familyfeudincompose.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.co.familyfeudincompose.data.FamilyFeudRepository
import com.cerve.co.familyfeudincompose.data.database.entity.Team
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

    val hasGone = mutableStateListOf<String>()

    val createdTeams = repository.fetchAllTeams()
        .stateIn(viewModelScope, WhileSubscribed(), emptyList())

    private val _teamNowPlaying = MutableStateFlow<Team?>(null)
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
                            points = card.points,
                            answered = false
                        )
                    }
                ).also { questions.add(it) }
            }
        }
    }

    fun nextTeam() = viewModelScope.launch {
        createdTeams.value.filterNot { it.name in hasGone }
            .random().also { _teamNowPlaying.emit(it) }
    }

    fun selectQuestion(index: Int) = viewModelScope.launch {
        _currentQuestion.update { questions[index] }
        questions.removeAt(index)
    }

    fun addStrike() { _strikeAccumulation.update { it + 1 } }

    fun createNewTeam(name: String, playerCount: Int) = viewModelScope.launch {
        repository.createTeam(name, playerCount)
    }

    fun awardPoints(points: Int, team: Team) = viewModelScope.launch {

        println(team.points)
        println(points)
        println(team.points + points)

        val updated = team.copy(points = team.points + points)
        repository.updatePoints(updated)

    }

}