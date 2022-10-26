package com.cerve.co.familyfeudincompose.ui.component

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cerve.co.familyfeudincompose.data.database.entity.TeamCard
import com.cerve.co.familyfeudincompose.ui.model.AnswerCardState
import com.cerve.co.familyfeudincompose.ui.model.QuestionCardState
import com.cerve.co.familyfeudincompose.ui.theme.FamilyFeudInComposeTheme
import com.cerve.co.familyfeudincompose.ui.util.DefaultPreview

@Composable
fun ThemedCard(
    state: AnswerCardState,
    showAswer: Boolean,
    modifier : Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary
) {

    val density = LocalDensity.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height((54.dp).times(2)),
        elevation = 0.dp,
        border = BorderStroke(color = color.copy(alpha = 0.5F), width = 2.dp),
        backgroundColor = color.copy(alpha = 0.2F)
    ) {

        AnimatedVisibility(
            visible = showAswer,
            enter = slideInVertically {
                // Slide in from 40 dp from the top.
                with(density) { -40.dp.roundToPx() }
            } + expandVertically(
                // Expand from the top.
                expandFrom = Alignment.Top
            ) + fadeIn(
                // Fade in with the initial alpha of 0.3f.
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            Box(
                modifier = Modifier.padding(4.dp),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${state.answer}\n${state.points}",
                    style = typography.body1.copy(
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center
                    )

                )
            }
        }
    }
}

@Composable
fun ThemedCard(
    state: QuestionCardState,
    modifier : Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
    lines: Int = 6
) {

    Box(
        modifier = modifier
            .flashCards(
                color = color,
                lines = lines
            )
    ) {

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = state.question,
            style = typography.h2
                .copy(fontWeight = FontWeight.Black)
        )

    }

}

@Composable
fun ThemedCard(
    state: TeamCard,
    modifier : Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
    lines: Int = 6
) {

    Box(
        modifier = modifier.flashCards(
            color = color,
            lines = lines
        )
    ) {

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = state.name,
            style = typography.body1
                .copy(fontWeight = FontWeight.Black),
            overflow = TextOverflow.Ellipsis
        )

    }

}

@DefaultPreview
@Composable
fun AnswerCardPreview() {
    FamilyFeudInComposeTheme {
        ThemedCard(state = QuestionCardState(
            question = "Lorem ipsum dolor sit amet?",
            answerCards = listOf()
        ))
    }
}