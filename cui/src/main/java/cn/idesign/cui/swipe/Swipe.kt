package cn.idesign.cui.swipe

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalComposeUiApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun Swipe(
    state: SwipeState = rememberSwipeState(),
    threshold: Float = 0.3f,
    direction: SwipeDirection = SwipeDirection.RightToLeft,
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
    val scope = rememberCoroutineScope()
    val swipeableState = rememberSwipeableState(0)
    val anchors by remember(backgroundWidthPx, direction) {
        if (direction == SwipeDirection.RightToLeft) {
            mutableStateOf(mapOf(0f to 0, -backgroundWidthPx.toFloat() to 1))
        } else {
            mutableStateOf(mapOf(0f to 0, backgroundWidthPx.toFloat() to 1))
        }
    }

    remember(state.currentValue) {
        when (state.currentValue) {
            SwipeValue.Hidden -> scope.launch {
                swipeableState.animateTo(0)
            }
            SwipeValue.Open -> scope.launch { swipeableState.animateTo(1) }
        }

    }


    val swipeModifier = if (backgroundWidthPx > 0) Modifier.swipeable(
        state = swipeableState,
        anchors = anchors,
        thresholds = { _, _ -> FractionalThreshold(threshold) },
        orientation = Orientation.Horizontal,
    ) else Modifier
    Box(modifier = Modifier
        .onSizeChanged {
            boxWidthPx = it.width
            boxHeightPx = it.height
        }
        .clipToBounds()
        .then(swipeModifier)
    ) {

        val backgroundOffsetX = when (direction) {
            SwipeDirection.LeftToRight -> -backgroundWidthPx + swipeableState.offset.value.roundToInt()
            SwipeDirection.RightToLeft -> boxWidthPx + swipeableState.offset.value.roundToInt()
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .offset {
                IntOffset(
                    x = swipeableState.offset.value.roundToInt(),
                    y = 0
                )
            }) {
            content()
        }
        Box(modifier = Modifier
            .height(with(LocalDensity.current) { boxHeightPx.toDp() })
            .onSizeChanged {
                backgroundWidthPx = it.width
            }
            .offset {
                IntOffset(
                    x = backgroundOffsetX,
                    y = 0
                )
            }
        ) {
            background()
        }

    }
}

sealed class SwipeDirection {
    object LeftToRight : SwipeDirection()
    object RightToLeft : SwipeDirection()
}

enum class SwipeValue {
    Hidden,
    Open,
}


@Composable
fun rememberSwipeState(
    initialValue: SwipeValue = SwipeValue.Hidden
): SwipeState = rememberSaveable(saver = SwipeState.SAVER) {
    SwipeState(
        initialValue = initialValue,
    )
}

@OptIn(ExperimentalMaterialApi::class)
class SwipeState(
    val initialValue: SwipeValue,
) {

    private var _currentValue: SwipeValue by mutableStateOf(initialValue)

    val currentValue: SwipeValue
        get() = _currentValue

    fun open() {
        if (_currentValue != SwipeValue.Open) {
            _currentValue = SwipeValue.Open
        }
    }

    fun close() {
        if (_currentValue != SwipeValue.Hidden) {
            _currentValue = SwipeValue.Hidden
        }
    }

    companion object {
        val SAVER: Saver<SwipeState, *> = Saver(
            save = {
                it.initialValue
            },
            restore = {
                SwipeState(it)
            }
        )
    }
}