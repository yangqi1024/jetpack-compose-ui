package cn.idesign.cui.swipe

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import kotlin.math.abs
import kotlin.math.roundToInt

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SwipeCopy(
    threshold: Float = 0.5f,
    background: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    var boxWidthPx by remember {
        mutableStateOf(0)
    }
    var boxHeightPx by remember {
        mutableStateOf(0)
    }
    var backgroundWidthPx by remember {
        mutableStateOf(0)
    }
    var needConsume by remember {
        mutableStateOf(0f)
    }
    val animateOffset by animateFloatAsState(needConsume)

    var offsetX by remember() { mutableStateOf(0f) }
    Box(modifier = Modifier
        .onSizeChanged {
            boxWidthPx = it.width
            boxHeightPx = it.height
            Log.d("Swipe", "boxWidthPx:${boxWidthPx}")
        }
        .clipToBounds()

        .pointerInput(Unit) {
            detectHorizontalDragGestures(
                onDragEnd = {
                    //计算当前offsetX 是否在阀值
                    val offsetXPercent = abs(offsetX) / backgroundWidthPx
                    if (offsetXPercent == 1f) {
                        //已经打开
                        needConsume = 0f
                    } else if (offsetXPercent == 0f) {
                        //已经关闭
                        needConsume = 0f
                    } else if (offsetXPercent >= threshold) {
                        //需要打开
                        needConsume = (-(backgroundWidthPx + offsetX))
                    } else {
                        //需要关闭
                        needConsume = -offsetX
                    }
                    Log.d("Swipe", "offsetXPercent:${offsetXPercent}")
                },
                onDragCancel = {},
                onDragStart = {},
                onHorizontalDrag = { change, dragAmount ->
                    offsetX = (offsetX + dragAmount).coerceIn(-backgroundWidthPx.toFloat(), 0f)
                    val offset = change.position.x.coerceIn(0f, boxWidthPx.toFloat())
                    Log.d("Swipe", "offset:${offset},offsetX:${offsetX}")
                },
            )
        }
    ) {


        Box(modifier = Modifier
            .fillMaxWidth()
            .offset {
                IntOffset(
                    x = offsetX.roundToInt() + animateOffset.roundToInt(),
                    y = 0
                )
            }) {
            content()
        }
        Box(modifier = Modifier
            .height(with(LocalDensity.current) { boxHeightPx.toDp() })
            .onSizeChanged {
                backgroundWidthPx = it.width
                Log.d("Swipe", "backgroundWidthPx:${backgroundWidthPx},height:${it.height}")
            }
            .offset {
                IntOffset(
                    x = boxWidthPx + offsetX.roundToInt() + animateOffset.roundToInt(),
                    y = 0
                )
            }) {
            background()
        }
    }
}