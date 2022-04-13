package cn.idesign.cui.indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Indicator(
    pageCount: Int,
    currentPage: Int,
    indicatorProgress: Float,
    modifier: Modifier = Modifier,
    activeColor: Color = MaterialTheme.colors.primary.copy(ContentAlpha.high),
    inactiveColor: Color = activeColor.copy(ContentAlpha.disabled),
    indicatorShape: Shape = CircleShape,
    inactiveIndicatorWidth: Dp = 8.dp,
    inactiveIndicatorHeight: Dp = if (indicatorShape == CircleShape) inactiveIndicatorWidth else inactiveIndicatorWidth / 2,
    activeIndicatorWidth: Dp = inactiveIndicatorWidth,
    activeIndicatorHeight: Dp = if (indicatorShape == CircleShape) activeIndicatorWidth else activeIndicatorWidth / 2,
    spacing: Dp = inactiveIndicatorWidth,
    mode: IndicatorMode = IndicatorMode.Normal,
    scale: Float = 1.2f,
) {
    val spacingPx = LocalDensity.current.run { spacing.roundToPx() }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val indicatorModifier = Modifier
                .size(width = inactiveIndicatorWidth, height = inactiveIndicatorHeight)
                .background(color = inactiveColor, shape = indicatorShape)

            repeat(pageCount) {
                Box(indicatorModifier)
            }
        }

        when (mode) {
            is IndicatorMode.Smooth -> IndicatorSmooth(
                pageCount = pageCount,
                currentPage= currentPage,
                indicatorProgress= indicatorProgress,
                activeIndicatorWidth = activeIndicatorWidth,
                activeIndicatorHeight = activeIndicatorHeight,
                activeColor = activeColor,
                indicatorShape = indicatorShape,
                spacingPx = spacingPx
            )

            is IndicatorMode.Scale -> IndicatorScale(
                pageCount= pageCount,
                currentPage= currentPage,
                indicatorProgress= indicatorProgress,
                activeIndicatorWidth = activeIndicatorWidth,
                activeIndicatorHeight = activeIndicatorHeight,
                activeColor = activeColor,
                indicatorShape = indicatorShape,
                spacingPx = spacingPx,
                scale = scale,
            )
            is IndicatorMode.Color -> IndicatorColor(
                pageCount= pageCount,
                currentPage= currentPage,
                indicatorProgress= indicatorProgress,
                activeIndicatorWidth = activeIndicatorWidth,
                activeIndicatorHeight = activeIndicatorHeight,
                activeColor = activeColor,
                indicatorShape = indicatorShape,
                spacingPx = spacingPx,
            )
            is IndicatorMode.Worm -> IndicatorWorm(
                pageCount  = pageCount,
                currentPage= currentPage,
                indicatorProgress= indicatorProgress,
                activeIndicatorWidth = activeIndicatorWidth,
                activeIndicatorHeight = activeIndicatorHeight,
                activeColor = activeColor,
                indicatorShape = indicatorShape,
                spacingPx = spacingPx,
            )

            else -> IndicatorNormal(
                pageCount  = pageCount,
                currentPage= currentPage,
                indicatorProgress= indicatorProgress,
                activeIndicatorWidth = activeIndicatorWidth,
                activeIndicatorHeight = activeIndicatorHeight,
                activeColor = activeColor,
                indicatorShape = indicatorShape,
                spacingPx = spacingPx
            )
        }
    }
}