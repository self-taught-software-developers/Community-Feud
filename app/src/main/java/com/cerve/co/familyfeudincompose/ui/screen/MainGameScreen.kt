package com.cerve.co.familyfeudincompose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cerve.co.familyfeudincompose.data.database.entity.Team
import com.cerve.co.familyfeudincompose.ui.model.QuestionCardState
import com.cerve.co.familyfeudincompose.ui.theme.FamilyFeudInComposeTheme

@Composable
fun MainGamingScreen(
    question: QuestionCardState?,
    questionCardList: List<QuestionCardState>,
    onSelectQuestion: (Int) -> Unit,
    modifier: Modifier = Modifier,
    currentTeam: Team?,
    onAwardPoints: (Int, Team) -> Unit = {_, _ -> },
    onNextTeam: () -> Unit = { },
    strikes: Int? = 0,
    onAddStrike: () -> Unit = { },
    onNavigateForward: () -> Unit
) {

    LaunchedEffect(Unit) {
        onNextTeam()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            LazyRow(modifier = Modifier.statusBarsPadding()) {
                itemsIndexed(questionCardList) { index, card ->
                    Card(
                        modifier = Modifier
                            .size(64.dp)
                            .clickable { onSelectQuestion(index) }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = card.question)
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar(modifier = Modifier.navigationBarsPadding()) {

                IconButton(onClick = { onNextTeam() }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = Icons.Default.Close.name
                    )
                }
                IconButton(onClick = { onAddStrike() }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = Icons.Default.Close.name
                    )
                }
                IconButton(onClick = { onNavigateForward() }) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = Icons.Default.Done.name
                    )
                }
            }
        },
        floatingActionButton = {
//            if (teamList.size >= 2) {
//
//                ExtendedFloatingActionButton(
//                    text = { Text(text = "Play Game") },
//                    onClick = { onNavigate() }
//                )
//
//            }
        }

    ) { bounds ->

        Box(modifier = Modifier.padding(bounds)) {
            currentTeam?.let {
                Column {

                    Text(text = currentTeam.name)
                    question?.let {
                        Text(text = question.question)

                        question.answerCards.forEach {
                            Text(
                                modifier = Modifier
                                    .clickable { onAwardPoints(it.points, currentTeam) },
                                text = it.answer
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