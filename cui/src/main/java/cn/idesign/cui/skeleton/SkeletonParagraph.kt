package cn.idesign.cui.skeleton

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SkeletonParagraph(
    modifier: Modifier = Modifier,
    animated: Boolean = false,
    rows: Int = 3,
    height: Dp = 16.dp,
    brushColorList: List<Color> = Skeleton.brushColorList,
) {
    if (animated) {
        var width by remember {
            mutableStateOf(0)
        }
        val transition = rememberInfiniteTransition()
        val translateAnim by transition.animateFloat(
            initialValue = 0f,
            targetValue = width.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(durationMillis = 1400),
                RepeatMode.Restart
            )
        )
        val brush = Brush.linearGradient(
            colors = brushColorList,
            start = Offset(0f, 0f),
            end = Offset(translateAnim, 0f)
        )

        Column(
            modifier = Modifier
                .onSizeChanged { size ->
                    width = size.width * 4
                },
            verticalArrangement = Arrangement.spacedBy(height)
        ) {
            repeat(rows) { index ->
                val widthFraction = when (index) {
                    0 -> 0.3f
                    rows - 1 -> 0.6f
                    else -> 1f
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(widthFraction)
                        .height(height)
                        .background(brush = brush)
                )
            }
        }
    } else {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(height)
        ) {
            repeat(rows) { index ->
                val widthFraction = when (index) {
                    0 -> 0.3f
                    rows - 1 -> 0.6f
                    else -> 1f
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(widthFraction)
                        .height(height)
                        .background(Skeleton.color)
                )
            }
        }
    }

}