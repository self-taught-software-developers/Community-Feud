package com.cerve.co.familyfeudincompose.ui.component

import androidx.compose.material3.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun ThiccDivider() {

    Divider(
        thickness = 4.dp,
        color = MaterialTheme.colors.primary
    )

}