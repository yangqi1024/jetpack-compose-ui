package cn.idesign.cui.indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import kotlin.math.floor

@Composable
internal fun IndicatorNormal(
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
    val offset = (currentPage + indicatorProgress).coerceIn(0f,(pageCount-1).toFloat())
    Box(
        Modifier
            .offset {
                IntOffset(

                    x = ((spacingPx + activeIndicatorWidthPx) * floor(offset)).toInt(),
                    y = 0
                )
            }
            .size(width = activeIndicatorWidth, height = activeIndicatorHeight)
            .background(
                color = activeColor,
                shape = indicatorShape,
            )
    )
}