package com.cerve.co.familyfeudincompose.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomBarButton(
    text: String,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {

    Column(modifier = modifier.navigationBarsPadding()) {
        ThiccDivider()

        BottomAppBar(
            modifier = modifier
                .clickable {
                    if (enabled) {
                        onClick()
                    }
                },
            backgroundColor = Color.Unspecified,
            elevation = 0.dp
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Center
            ) {
                Text(
                    modifier = Modifier,
                    text = text
                )
            }

        }
    }

}