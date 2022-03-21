package cn.idesign.cui.indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.util.lerp
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor

@Composable
internal fun IndicatorScale(
    pageCount: Int,
    currentPage: Int,
    indicatorProgress: Float,
    activeIndicatorWidth: Dp,
    activeIndicatorHeight: Dp,
    activeColor: Color,
    indicatorShape: Shape,
    spacingPx: Int,
    scale: Float,
) {
    val activeIndicatorWidthPx = LocalDensity.current.run { activeIndicatorWidth.roundToPx() }
    val offset = (currentPage + indicatorProgress).coerceIn(0f, (pageCount - 1).toFloat())
    val progress = if (indicatorProgress >= 0.0) indicatorProgress else 1 - abs(indicatorProgress)

    Box {
        Box(
            Modifier
                .offset {
                    IntOffset(
                        x = ((spacingPx + activeIndicatorWidthPx) * floor(offset)).toInt(),
                        y = 0
                    )
                }
                .graphicsLayer {
                    lerp(
                        start = scale,
                        stop = 1f,
                        fraction = progress.coerceIn(0f, 1f)
                    ).also { percent ->
                        scaleX = percent
                        scaleY = percent

                    }

                    lerp(
                        start = 1f,
                        stop = 0f,
                        fraction = progress.coerceIn(0f, 1f)
                    ).also { percent ->
                        alpha = percent
                    }
                }
                .size(width = activeIndicatorWidth, height = activeIndicatorHeight)
                .background(
                    color = activeColor,
                    shape = indicatorShape,
                )
        )
        Box(

            Modifier
                .offset {
                    IntOffset(
                        x = ((spacingPx + activeIndicatorWidthPx) * ceil(offset)).toInt(),
                        y = 0
                    )
                }
                .graphicsLayer {
                    lerp(
                        start = 1f,
                        stop = scale,
                        fraction = progress.coerceIn(0f, 1f)
                    ).also { percent ->
                        scaleX = percent
                        scaleY = percent
                    }

                    lerp(
                        start = 0f,
                        stop = 1f,
                        fraction = progress.coerceIn(0f, 1f)
                    ).also { percent ->
                        alpha = percent
                    }
                }
                .size(width = activeIndicatorWidth, height = activeIndicatorHeight)
                .background(
                    color = activeColor,
                    shape = indicatorShape,
                )
        )
    }
}