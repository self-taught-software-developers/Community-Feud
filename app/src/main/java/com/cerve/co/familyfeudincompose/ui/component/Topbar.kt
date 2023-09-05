package com.cerve.co.familyfeudincompose.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cerve.co.familyfeudincompose.ui.model.QuestionCardState

@Composable
fun ThemedTopBar(
    questionCardList: List<QuestionCardState>,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit = { }
) {

    Column(modifier.statusBarsPadding()) {
        LazyRow(modifier = Modifier.height(56.dp)) {
            itemsIndexed(questionCardList) { index, card ->
                ThemedCard(
                    modifier = Modifier.clickable { onClick(index) },
                    state = card
                )
            }
        }
        ThiccDivider()
    }
}