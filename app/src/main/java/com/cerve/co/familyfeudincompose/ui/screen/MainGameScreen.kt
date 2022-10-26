package com.cerve.co.familyfeudincompose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cerve.co.familyfeudincompose.data.database.entity.AnswerCard
import com.cerve.co.familyfeudincompose.data.database.entity.TeamCard
import com.cerve.co.familyfeudincompose.ui.component.BottomBarButton
import com.cerve.co.familyfeudincompose.ui.component.ThemedCard
import com.cerve.co.familyfeudincompose.ui.component.ThemedTopBar
import com.cerve.co.familyfeudincompose.ui.model.QuestionCardState
import com.cerve.co.familyfeudincompose.ui.theme.FamilyFeudInComposeTheme

@Composable
fun MainGamingScreen(
    question: QuestionCardState?,
    questionCardList: List<QuestionCardState>,
    onSelectQuestion: (Int) -> Unit,
    modifier: Modifier = Modifier,
    currentTeam: TeamCard?,
    onAwardPoints: (Int, TeamCard) -> Unit = { _, _ -> },
    onNextTeam: (Boolean) -> Unit = { },
    strikes: Int? = 0,
    onAddStrike: () -> Unit = { },
    onNavigateForward: () -> Unit
) {

    LaunchedEffect(Unit) {
        onNextTeam(false)
    }

    LaunchedEffect(strikes) {
        println(strikes)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            ThemedTopBar(
                questionCardList = questionCardList,
                onClick = onSelectQuestion
            )
        },
        bottomBar = {
            BottomBarButton(
                text = "End Game",
                enabled = true
            ) { onNavigateForward() }
//            BottomAppBar(modifier = Modifier.navigationBarsPadding()) {
//
//                IconButton(onClick = { onNextTeam(true) }) {
//                    Icon(
//                        imageVector = Icons.Default.Close,
//                        contentDescription = Icons.Default.Close.name
//                    )
//                }
//                IconButton(onClick = { onAddStrike() }) {
//                    Icon(
//                        imageVector = Icons.Default.Close,
//                        contentDescription = Icons.Default.Close.name
//                    )
//                }
//                IconButton(onClick = { onNavigateForward() }) {
//                    Icon(
//                        imageVector = Icons.Default.Done,
//                        contentDescription = Icons.Default.Done.name
//                    )
//                }
//            }
        },
        floatingActionButton = {

            ExtendedFloatingActionButton(
                text = { Text(text = "strikes: ${strikes.toString()}") },
                onClick = { onAddStrike() }
            )

        }

    ) { bounds ->

        val list = remember(question?.answerCards) {
            mutableStateListOf(
                *(question?.answerCards ?: emptyList()).toTypedArray()
            )
        }

        Column(
            modifier = Modifier
                .padding(bounds)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            currentTeam?.let {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Team: ${currentTeam.name}",
                    style = MaterialTheme.typography.h4
                )

                question?.let {
                    Text(
                        text = question.question,
                        style = MaterialTheme.typography.subtitle1
                    )

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(list) { index, card ->
                            ThemedCard(
                                state = card,
                                showAswer = card.answered,
                                modifier = Modifier.clickable {
                                    onAwardPoints(card.points, currentTeam)
                                    list[index] = card.toAnswered()
                                }
                            )
                        }
                    }

                }

            }
        }
    }

}

@Preview
@Composable
fun MainGamingScreenPreview() {
    FamilyFeudInComposeTheme {
        MainGamingScreen(
            questionCardList = emptyList(),
            onSelectQuestion = { },
            question = QuestionCardState(
                question = "Lorem ipsum dolor sit amet?",
                answerCards = emptyList()
            ),
            currentTeam = null
        ) {

        }
    }
}