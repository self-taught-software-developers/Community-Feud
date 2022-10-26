package com.cerve.co.familyfeudincompose.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.flashCards(color: Color, lines: Int) = this
    .padding(8.dp)
    .clip(RoundedCornerShape(5))
    .size(56.dp)
    .drawWithCache {

        onDrawBehind {

            val (width, height) = size
            val lineGap = height / lines

            drawRect(
                color = color,
                topLeft = Offset(0F, 0F),
                size = Size(width = width/15 , height = height)
            )

            repeat(lines) { y ->
                drawLine(
                    color = color.copy(alpha = 0.2F),
                    start = Offset(0F, lineGap * y),
                    end = Offset(width, lineGap * y)
                )
            }

        }
    }