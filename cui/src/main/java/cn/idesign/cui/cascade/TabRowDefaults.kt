package cn.idesign.cui.cascade

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object TabRowDefaults {
    /**
     * Default [Divider], which will be positioned at the bottom of the [TabRow], underneath the
     * indicator.
     *
     * @param modifier modifier for the divider's layout
     * @param thickness thickness of the divider
     * @param color color of the divider
     */
    @Composable
    fun Divider(
        modifier: Modifier = Modifier,
        thickness: Dp = DividerThickness,
        color: Color = LocalContentColor.current.copy(alpha = DividerOpacity)
    ) {
        androidx.compose.material.Divider(modifier = modifier, thickness = thickness, color = color)
    }

    /**
     * Default indicator, which will be positioned at the bottom of the [TabRow], on top of the
     * divider.
     *
     * @param modifier modifier for the indicator's layout
     * @param height height of the indicator
     * @param color color of the indicator
     */
    @Composable
    fun Indicator(
        modifier: Modifier = Modifier,
        height: Dp = IndicatorHeight,
        color: Color = MaterialTheme.colors.primary
    ) {
        Box(
            modifier
                .fillMaxWidth()
                .height(height)
                .background(color = color)
        )
    }

    /**
     * [Modifier] that takes up all the available width inside the [TabRow], and then animates
     * the offset of the indicator it is applied to, depending on the [currentTabPosition].
     *
     * @param currentTabPosition [TabPosition] of the currently selected tab. This is used to
     * calculate the offset of the indicator this modifier is applied to, as well as its width.
     */
    fun Modifier.tabIndicatorOffset(
        currentTabPosition: TabPosition
    ): Modifier = composed(
        inspectorInfo = debugInspectorInfo {
            name = "tabIndicatorOffset"
            value = currentTabPosition
        }
    ) {
        val currentTabWidth by animateDpAsState(
            targetValue = currentTabPosition.width,
            animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
        )
        val indicatorOffset by animateDpAsState(
            targetValue = currentTabPosition.left,
            animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
        )
        fillMaxWidth()
            .wrapContentSize(Alignment.BottomStart)
            .offset(x = indicatorOffset)
            .width(currentTabWidth)
    }

    /**
     * Default opacity for the color of [Divider]
     */
    const val DividerOpacity = 0.12f

    /**
     * Default thickness for [Divider]
     */
    val DividerThickness = 1.dp

    /**
     * Default height for [Indicator]
     */
    val IndicatorHeight = 2.dp

    /**
     * The default padding from the starting edge before a tab in a [ScrollableTabRow].
     */
    val ScrollableTabRowPadding = 52.dp
}