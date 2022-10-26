package com.cerve.co.familyfeudincompose.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cerve.co.familyfeudincompose.data.database.entity.TeamCard
import com.cerve.co.familyfeudincompose.ui.component.BottomBarButton
import com.cerve.co.familyfeudincompose.ui.component.ThemedCard
import com.cerve.co.familyfeudincompose.ui.component.ThemedTopBar
import com.cerve.co.familyfeudincompose.ui.component.ThiccDivider
import com.cerve.co.familyfeudincompose.ui.theme.FamilyFeudInComposeTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TeamCreationScreen(
    teamList: List<TeamCard>,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Int) -> Unit = { },
    onClick: (String, Int) -> Unit = { _, _ -> }
) {

    val focusManager = LocalFocusManager.current
    var text by remember { mutableStateOf("") }
    var countText by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier,
        topBar = {
            ThemedTopBar(
                teamList = teamList,
                onDeleteClick = onDeleteClick
            )
        },
        bottomBar = {
            BottomBarButton(
                text = "Add Team",
                enabled = text.isNotBlank() && countText.isNotBlank()
            ) { onClick(text, countText.toInt()) }
        },
        floatingActionButton = {
            if (teamList.size >= 2) {

                ExtendedFloatingActionButton(
                    text = { Text(text = "Play Game") },
                    onClick = { onNavigate() }
                )

            }
        }
    ) { bounds ->

        Column(
            modifier = Modifier
                .padding(bounds)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Family Feud",
                style = typography.h3
            )

            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                singleLine = true,
                label = { Text(text = "Team Name") },
                keyboardActions = KeyboardActions {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )

            OutlinedTextField(
                value = countText,
                onValueChange = { countText = it },
                singleLine = true,
                label = { Text(text = "Team Member Count") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions {
                    focusManager.clearFocus()
                }
            )
            
        }

    }

}

@Preview
@Composable
fun TeamCreationScreenPreview() {
    FamilyFeudInComposeTheme {
        TeamCreationScreen(
            teamList = emptyList(),
            onNavigate = { }
        )
    }
}