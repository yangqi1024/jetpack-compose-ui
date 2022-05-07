package cn.idesign.cui.rate

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.annotation.IntRange
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlin.math.floor
import kotlin.math.round

/**
 * 星星评价组件
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Rate(
    count: Int = 5,
    size: DpSize = DpSize(20.dp, 20.dp),
    horizontalSpacing: Dp = 4.dp,
    @SuppressLint("SupportAnnotationUsage") @IntRange(from = 0L) value: Float = Float.NaN,
    activeColor: Color = Color(0xFFF7BA2A),
    normalColor: Color = Color(0xFFC6D1DE),
    activeIcon: ImageVector = Icons.Rounded.Star,
    normalIcon: ImageVector = Icons.Rounded.StarBorder,
    allowHalf: Boolean = false,
    readOnly: Boolean = false,
    onChange: (value: Float) -> Unit
) {
    val widthPx = with(LocalDensity.current) { size.width.toPx() }
    val spacingPx = with(LocalDensity.current) { horizontalSpacing.toPx() }
    var boxWidthPx by remember {
        mutableStateOf(0)
    }
    Box(
        Modifier
            .onSizeChanged {
                boxWidthPx = it.width
            }
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {},
                    onDragCancel = {},
                    onDragStart = {},
                    onHorizontalDrag = { change, dragAmount ->
                        if (readOnly) {
                            return@detectHorizontalDragGestures
                        }
                        val offset = change.position.x.coerceIn(0f, boxWidthPx.toFloat())
                        val index = (offset / (widthPx + spacingPx))
                        val fixIndex = (if (allowHalf) index.step() else round(index)).coerceIn(
                            0f,
                            count.toFloat()
                        )
                        onChange(fixIndex)
                    },
                )
            }
            .pointerInteropFilter {
                if (readOnly) {
                    return@pointerInteropFilter false
                }
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        val offset = it.x.coerceIn(0f, boxWidthPx.toFloat())
                        val index =
                            (offset / (widthPx + spacingPx)).coerceIn(0f, count.toFloat())
                        val fixIndex = (if (allowHalf) index.step() else round(index)).coerceIn(
                            0f,
                            count.toFloat()
                        )
                        onChange(fixIndex)
                    }
                }
                true
            },
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(horizontalSpacing)) {
            repeat(count) {
                Icon(
                    normalIcon,
                    contentDescription = "star",
                    tint = normalColor,
                    modifier = Modifier.size(size)
                )
            }
        }
        if (!value.isNaN()) {
            Row(horizontalArrangement = Arrangement.spacedBy(horizontalSpacing)) {
                repeat(floor(value).toInt()) {
                    Icon(
                        activeIcon,
                        contentDescription = "star",
                        tint = activeColor,
                        modifier = Modifier
                            .size(size)
                    )
                }
                if (allowHalf && value > floor(value)) {
                    val percent = value - floor(value)
                    Box {
                        Icon(
                            normalIcon,
                            contentDescription = "star",
                            tint = normalColor,
                            modifier = Modifier.size(size)
                        )
                        Icon(
                            activeIcon,
                            contentDescription = "star",
                            tint = activeColor,
                            modifier = Modifier
                                .size(size)
                                .clip(GenericShape { size, direction ->
                                    moveTo(0f, 0f)
                                    lineTo(size.width * percent, 0f)
                                    lineTo(size.width * percent, size.height)
                                    lineTo(0f, size.height)
                                    relativeLineTo(0f, -size.height)
                                })
                        )
                    }
                }

            }
        }
    }
}

fun Float.step(): Float {
    var value = this.toInt().toFloat()
    if (this < value.plus(0.5)) {
        if (this == 0f) {
            return 0f
        }
        value = value.plus(0.5).toFloat()
        return value
    } else {
        return round(this)
    }
}