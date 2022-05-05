package cn.idesign.cui.skeleton

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp

@Composable
fun SkeletonAvatar(
    modifier: Modifier = Modifier,
    animated: Boolean = false,
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
                tween(durationMillis = 1400, easing = LinearEasing),
                RepeatMode.Restart
            )
        )
        val brush = Brush.linearGradient(
            colors = brushColorList,
            start = Offset(0f, 0f),
            end = Offset(translateAnim, 0f)
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .onSizeChanged { size ->
                    width = size.width * 4
                }
                .clip(CircleShape)
                .background(brush = brush)
                .then(modifier)
        )
    } else {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Skeleton.color)
                .then(modifier)
        )
    }

}