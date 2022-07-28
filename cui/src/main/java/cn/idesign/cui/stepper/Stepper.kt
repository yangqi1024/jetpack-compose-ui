package cn.idesign.cui.stepper

import androidx.annotation.IntRange
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalAbsoluteElevation
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cn.idesign.cui.utils.calculateForegroundColor

@Composable
fun Stepper(
    modifier: Modifier = Modifier,
    state: StepperState = rememberStepperState(),
    height: Dp = 24.dp,
    inputWidth: Dp = 44.dp,
    max: Int = Int.MAX_VALUE,
    min: Int = Int.MIN_VALUE,
    step: Int = 1,
    inputReadOnly: Boolean = false,
    disable: Boolean = false,
    backgroundColor: Color = MaterialTheme.colors.surface,
) {
    val absoluteElevation = LocalAbsoluteElevation.current + 1.dp
    val foregroundColor = calculateForegroundColor(backgroundColor, absoluteElevation)
    val _backgroundColor = foregroundColor.compositeOver(backgroundColor)
    Row(
        modifier = modifier,
    ) {
        Icon(
            Icons.Default.Remove,
            contentDescription = null,
            modifier = Modifier
                .size(height)
                .clickable(indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    enabled = !disable && state.currentValue > min,
                    onClick = {
                        state.currentValue = state.currentValue
                            .minus(step)
                            .coerceIn(min, max)
                    })
                .padding(2.dp)
                .alpha(if (!disable && state.currentValue > min) ContentAlpha.high else ContentAlpha.disabled),
            tint = MaterialTheme.colors.onSurface
        )
        BasicTextField(
            value = state.currentValue.toString(),
            modifier = Modifier
                .size(inputWidth, height)
                .background(_backgroundColor)
                .alpha(if (!disable) ContentAlpha.high else ContentAlpha.disabled)
                .wrapContentHeight(),
            onValueChange = { value ->
                state.currentValue = value.toInt().coerceIn(min, max)
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.NumberPassword),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                .copy(color = MaterialTheme.colors.onSurface),
            maxLines = 1,
            singleLine = true,
            readOnly = inputReadOnly,
            enabled = !disable,
        )

        Icon(
            Icons.Default.Add,
            contentDescription = null,
            modifier = Modifier
                .size(height)
                .clickable(indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    enabled = !disable && state.currentValue < max,
                    onClick = {
                        state.currentValue = state.currentValue
                            .plus(step)
                            .coerceIn(min, max)
                    })
                .padding(2.dp)
                .alpha(if (!disable && state.currentValue < max) ContentAlpha.high else ContentAlpha.disabled),
            tint = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
fun rememberStepperState(
    @IntRange(from = 0) initialValue: Int = 0,
): StepperState = rememberSaveable(saver = StepperState.Saver) {
    StepperState(
        initialValue = initialValue,
    )
}

class StepperState(
    @IntRange(from = 0) var initialValue: Int = 0,
) {
    private var _currentValue: Int by mutableStateOf(initialValue)

    @get:IntRange(from = 0)
    var currentValue: Int
        get() = _currentValue
        internal set(value) {
            if (value != _currentValue) {
                _currentValue = value
            }
        }

    companion object {
        val Saver: Saver<StepperState, *> = Saver(
            save = {
                it.initialValue
            },
            restore = {
                StepperState(
                    initialValue = it,
                )
            }
        )
    }
}