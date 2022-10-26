package com.cerve.co.familyfeudincompose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.familyfeudincompose.data.database.entity.TeamCard
import com.cerve.co.familyfeudincompose.ui.component.BottomBarButton
import com.cerve.co.familyfeudincompose.ui.theme.FamilyFeudInComposeTheme

@Composable
fun WinnerScreen(
    team: TeamCard?,
    modifier: Modifier = Modifier,
    onRestartGame: () -> Unit = { },
) {

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomBarButton(
                text = "Restart",
                enabled = true
            ) { onRestartGame() }
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
            }
        }

    }

}

@Preview
@Composable
fun WinnerScreenPreview() {
    FamilyFeudInComposeTheme {
        WinnerScreen(
            team = TeamCard(
                name = "Lorem ipsum",
                points = 200,
                turns = 5
            )
        )
    }
}