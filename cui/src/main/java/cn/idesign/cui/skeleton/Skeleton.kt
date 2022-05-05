package cn.idesign.cui.skeleton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun Skeleton(
    modifier: Modifier = Modifier,
    loading: Boolean = true,
    animated: Boolean = false,
    avatar: Boolean = false,
    brushColorList: List<Color> = Skeleton.brushColorList,
    rows: Int = 3,
    content: (@Composable () -> Unit)? = null,
) {
    Skeleton(
        modifier = modifier,
        loading = loading,
        animated = animated,
        content = content,
        defaultContent = {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                if (avatar) {
                    SkeletonAvatar(
                        brushColorList = brushColorList,
                        animated = animated,
                    )
                }
                SkeletonParagraph(rows = rows, brushColorList = brushColorList, animated = animated)
            }
        }
    )

}

@Composable
fun Skeleton(
    modifier: Modifier = Modifier,
    loading: Boolean = true,
    animated: Boolean = false,
    defaultContent: @Composable () -> Unit,
    content: (@Composable () -> Unit)? = null,
) {
//    var width by remember {
//        mutableStateOf(0)
//    }
//    val transition = rememberInfiniteTransition()
//    val translateAnim by transition.animateFloat(
//        initialValue = 0f,
//        targetValue = width.toFloat(),
//        animationSpec = infiniteRepeatable(
//            tween(durationMillis = 1400, easing = FastOutSlowInEasing),
//            RepeatMode.Restart
//        )
//    )
//    val color = Color(190, 190, 190).copy(0.2f)
//    val shimmerColorShades = listOf(
//        color.copy(0.25f),
//        color.copy(0.37f),
//        color.copy(0.63f)
//    )
//    val brush = Brush.linearGradient(
//        colors = shimmerColorShades,
//        start = Offset(0f, 0f),
//        end = Offset(translateAnim, 0f)
//    )
//    Spacer(
//        modifier = Modifier
//            .fillMaxWidth()
//            .size(250.dp)
//            .onSizeChanged { size ->
//                width = size.width * 4
//            }
//            .background(brush = brush)
//    )

//    val skeletonScopeImpl = SkeletonScopeImpl().apply(defaultContent)
    if (loading) {
        defaultContent()
    } else {
        content?.invoke()
    }
}


object Skeleton {
    val color = Color(190, 190, 190).copy(0.2f)
    val iconColor = Color(0xffdcdde0)
    val brushColorList: List<Color> = listOf(
        color.copy(0.25f),
        color.copy(0.37f),
        color.copy(0.63f)
    )
}
