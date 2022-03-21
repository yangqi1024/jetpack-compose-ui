package cn.idesign.cui.indicator

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor

@Composable
internal fun IndicatorWorm(
    pageCount: Int,
    currentPage: Int,
    indicatorProgress: Float,
    activeIndicatorWidth: Dp,
    activeIndicatorHeight: Dp,
    activeColor: Color,
    indicatorShape: Shape,
    spacingPx: Int,
) {
    val activeIndicatorWidthPx = LocalDensity.current.run { activeIndicatorWidth.roundToPx() }
    val activeIndicatorHeightPx = LocalDensity.current.run { activeIndicatorHeight.roundToPx() }
    val distance = spacingPx + activeIndicatorWidthPx


    val progress = if (indicatorProgress >= 0.0) indicatorProgress else 1 - abs(indicatorProgress)
    val offset = (currentPage + indicatorProgress).coerceIn(0f, (pageCount - 1).toFloat())

    Canvas(modifier = Modifier.wrapContentSize(), onDraw = {
        val paint = Paint().apply {
            color = activeColor

        }
        drawIntoCanvas { canvas: Canvas ->
            canvas.drawRoundRect(
                left = if (progress >= 0.5f) {
                    (spacingPx + activeIndicatorWidthPx) * offset
                } else {
                    (spacingPx + activeIndicatorWidthPx) * floor(offset)
                },
                top = -activeIndicatorHeightPx / 2.toFloat(),
                bottom = activeIndicatorHeightPx / 2.toFloat(),
                right = when {
                    offset.toInt() == pageCount - 1 -> activeIndicatorWidthPx + (spacingPx + activeIndicatorWidthPx) * ceil(
                        offset
                    )
                    progress >= 0.5f -> {
                        activeIndicatorWidthPx + (spacingPx + activeIndicatorWidthPx) * ceil(
                            offset
                        )
                    }
                    else -> {
                        distance * floor(offset) + activeIndicatorWidthPx + distance * progress * 2
                    }
                },
                paint = paint,
                radiusX = if (indicatorShape == RectangleShape) 0f else activeIndicatorHeightPx / 2.toFloat(),
                radiusY = if (indicatorShape == RectangleShape) 0f else activeIndicatorHeightPx / 2.toFloat()
            )
        }
    }
    )
}