package com.cerve.co.familyfeudincompose.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cerve.co.familyfeudincompose.ui.model.QuestionCardState
import com.cerve.co.familyfeudincompose.ui.theme.FamilyFeudInComposeTheme
import com.cerve.co.familyfeudincompose.ui.util.DefaultPreview

@Composable
fun QuestionCard(
    state: QuestionCardState,
    modifier : Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary
) {

    Box(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(5))
            .fillMaxWidth(2f)
            .fillMaxHeight()
            .drawWithCache {

                onDrawBehind {

                    val (width, height) = size
                    val lineGap = height / 5

                    drawRect(
                        color = color,
                        topLeft = Offset(0F, 0F),
                        size = Size(width = width/25 ,height = height)
                    )

                    repeat(5) { y ->
                        drawLine(
                            color = color,
                            start = Offset(0F, lineGap * y),
                            end = Offset(width, lineGap * y)
                        )
                    }

                }
            },
    ) {

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = state.question,
            style = MaterialTheme.typography.h3
        )

    }

}

fun split(x: Int, n: Int) : List<Int> {

    return when {
        (x < n) -> emptyList()
        (x % n == 0) -> (0 until n).map { x / n }

        else -> {
            // up to n-(x % n) the values
            // will be x / n
            // after that the values
            // will be x / n + 1
            val zp = n - x % n
            val pp = x / n

            (0 until n)
                .map { if (it >= zp) { pp + 1 } else { pp } }

        }
    }

}

@DefaultPreview
@Composable
fun AnswerCardPreview() {
    FamilyFeudInComposeTheme {
        QuestionCard(state = QuestionCardState(
            question = "Lorem ipsum dolor sit amet?",
            answerCards = listOf()
        ))
    }
}