package com.cerve.co.familyfeudincompose.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cerve.co.familyfeudincompose.data.database.entity.Team
import com.cerve.co.familyfeudincompose.ui.theme.FamilyFeudInComposeTheme

@Composable
fun TeamCreationScreen(
    teamList: List<Team>,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    onClick: (String, Int) -> Unit = { _, _ -> }
) {

    val focusManager = LocalFocusManager.current
    var text by remember { mutableStateOf("") }
    var countText by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier,
        topBar = {
            LazyColumn(modifier = Modifier.statusBarsPadding()) {
                items(teamList) { team ->
                    Card(modifier = Modifier.size(64.dp)) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = team.name)
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar(modifier = Modifier.navigationBarsPadding()) {
                IconButton(
                    onClick = {
                        onClick(
                            text,
                            countText.toInt()
                        )
                    }
                ) { Icon(imageVector = Icons.Default.Add, contentDescription = Icons.Default.Add.name)}
            }
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
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                singleLine = true,
                keyboardActions = KeyboardActions {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )

            OutlinedTextField(
                value = countText,
                onValueChange = { countText = it },
                singleLine = true,
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