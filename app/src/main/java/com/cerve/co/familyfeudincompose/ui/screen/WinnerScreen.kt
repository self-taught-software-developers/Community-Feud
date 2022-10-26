package com.cerve.co.familyfeudincompose.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.familyfeudincompose.data.database.entity.Team
import com.cerve.co.familyfeudincompose.ui.theme.FamilyFeudInComposeTheme

@Composable
fun WinnerScreen(
    team: Team?,
    modifier: Modifier = Modifier,
    onRestartGame: () -> Unit = { },
) {

    Scaffold(
        modifier = modifier,
        topBar = {
//            LazyRow(modifier = Modifier.statusBarsPadding()) {
//                itemsIndexed(questionCardList) { index, card ->
//                    Card(
//                        modifier = Modifier
//                            .size(64.dp)
//                            .clickable { onSelectQuestion(index) }
//                    ) {
//                        Box(contentAlignment = Alignment.Center) {
//                            Text(text = card.question)
//                        }
//                    }
//                }
//            }
        },
        bottomBar = {
//            BottomAppBar(modifier = Modifier.navigationBarsPadding()) {
//
//                IconButton(onClick = { onNextTeam() }) {
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
//            }
        }
    ) { bounds ->

        Box(
            modifier = Modifier
                .padding(bounds)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            team?.let {
                Column(
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = team.name)
                    Text(text = team.points.toString())
                }
            } // TODO NO TEAM

        }

    }

}

@Preview
@Composable
fun WinnerScreenPreview() {
    FamilyFeudInComposeTheme {
        WinnerScreen(
            team = Team(
                name = "Lorem ipsum",
                points = 200,
                turns = 5
            )
        )
    }
}