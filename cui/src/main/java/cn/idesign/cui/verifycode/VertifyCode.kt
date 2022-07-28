package cn.idesign.cui.verifycode

import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.KeyEvent.ACTION_DOWN
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.input.CommitTextCommand
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly

/**
 * 验证码输入组件，支持下划线、正方形
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VerifyCode(
    modifier: Modifier = Modifier,
    count: Int = 6,
    space: Dp = 10.dp,
    size: DpSize = DpSize(40.dp, 40.dp),
    lineHeight: Dp = 2.dp,
    textFontSize: TextUnit = 32.sp,
    activeLineColor: Color = MaterialTheme.colors.onSurface,
    textColor: Color = activeLineColor,
    normalLineColor: Color = activeLineColor.copy(alpha = ContentAlpha.disabled),
    cursorLineWidth: Dp = 2.dp,
    cursorLineHeight: Dp = Dp.Unspecified,
    cursorLineHeightRatio: Float = 0.4f,
    cursorLineColor: Color = Color.Black,
    type: VerifyType = VerifyType.BottomLine,
    onComplete: (text: String) -> Unit,
) {
    val spacePx = with(LocalDensity.current) { space.toPx() }
    val lineHeightPx = with(LocalDensity.current) { lineHeight.toPx() }
    val cursorLineWidthPx = with(LocalDensity.current) { cursorLineWidth.toPx() }
    val textFontSizePx = with(LocalDensity.current) { textFontSize.toPx() }
    val infiniteTransition = rememberInfiniteTransition()
    val cursorAlphaState by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            repeatMode = RepeatMode.Reverse,
            animation = tween(easing = LinearEasing, delayMillis = 300)
        )
    )
    val textInputService = LocalTextInputService.current
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    var text by remember { mutableStateOf("") }
    val paint by remember {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = textColor.toArgb()
        paint.textSize = textFontSizePx
        paint.textAlign = Paint.Align.CENTER
        mutableStateOf(paint)
    }
    val boxWidthPx = with(LocalDensity.current) { size.width.toPx() }
    val boxHeightPx = with(LocalDensity.current) { size.height.toPx() }
    val maxWidth = size.width * count + space * (count - 1)
    Canvas(
        modifier = Modifier
            .width(maxWidth)
            .height(with(LocalDensity.current) { boxWidthPx.toDp() })
            .focusRequester(focusRequester)
            .onFocusChanged { state ->
                if (state.isFocused) {
                    //已经获取焦点
                    textInputService?.startInput(
                        value = TextFieldValue(""),
                        imeOptions = ImeOptions.Default.copy(
                            keyboardType = KeyboardType.NumberPassword,
                            imeAction = ImeAction.Done
                        ),
                        onEditCommand = { commandList ->
                            commandList.forEach { command ->
                                Log.d("VerifyCode", "command:${command}")
                                if (text.length < count) {
                                    when (command) {
                                        is CommitTextCommand -> if (command.text.isDigitsOnly()) text += command.text
                                    }
                                }
                                if (text.length == count) {
                                    //输入完成
                                    onComplete(text)
                                }
                            }
                        },
                        onImeActionPerformed = { imeAction ->
                            Log.d("VerifyCode", "imeAction:${imeAction}")
                            if (imeAction == ImeAction.Done) {
                                textInputService.hideSoftwareKeyboard()
                            }
                        }
                    )
                }
            }
            .focusTarget()
            .focusable()
            .onPreviewKeyEvent { keyEvent ->
                val nativeKeyEvent = keyEvent.nativeKeyEvent
                if (nativeKeyEvent.action == ACTION_DOWN && nativeKeyEvent.keyCode == android.view.KeyEvent.KEYCODE_DEL) {
                    text = text.dropLast(1)
                }
                false
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusRequester.requestFocus()
                        textInputService?.showSoftwareKeyboard()
                    })
            }
            .then(modifier)
    ) {
        val cursorLineHeightPx =
            if (cursorLineHeight == Dp.Unspecified) boxWidthPx * cursorLineHeightRatio else cursorLineHeight.toPx()
        //绘制下划线或者边框
        repeat(count) { index ->
            when (type) {
                VerifyType.BottomLine -> {
                    drawLine(
                        color = if (index <= text.length) activeLineColor else normalLineColor,
                        start = Offset(
                            (boxWidthPx + spacePx) * index,
                            boxHeightPx - lineHeightPx
                        ),
                        end = Offset(
                            (boxWidthPx + spacePx) * index + boxWidthPx,
                            boxHeightPx - lineHeightPx
                        ),
                        strokeWidth = lineHeightPx
                    )

                }
                VerifyType.Square -> {
                    drawRect(
                        color = if (index <= text.length) activeLineColor else normalLineColor,
                        topLeft = Offset(
                            (boxWidthPx + spacePx) * index,
                            0f,
                        ),
                        size = Size(boxWidthPx, boxHeightPx - lineHeightPx),
                        style = Stroke(width = lineHeightPx)
                    )
                }
            }
        }

        //绘制文字
        repeat(text.length) { index ->
            drawIntoCanvas { canvas ->
                val textStr = text[index].toString()
                val rect = Rect(
                    ((boxWidthPx + spacePx) * index).toInt(), 0,
                    ((boxWidthPx + spacePx) * index + boxWidthPx).toInt(), boxHeightPx.toInt()
                )
                val metrics = paint.fontMetrics
                val distance = (metrics.descent - metrics.ascent) / 2 - metrics.descent
                val baseline = rect.centerY() + distance

                Log.d("VerifyCode", "drawText")
                canvas.nativeCanvas.drawText(
                    textStr,
                    rect.centerX().toFloat(),
                    baseline,
                    paint
                )
            }
        }

        //绘制Cursor
        if (text.length < count) {
            drawLine(
                color = cursorLineColor,
                start = Offset(
                    (boxWidthPx + spacePx) * text.length + (boxWidthPx - cursorLineWidthPx) / 2,
                    (boxHeightPx - cursorLineHeightPx) / 2
                ),
                end = Offset(
                    (boxWidthPx + spacePx) * text.length + (boxWidthPx - cursorLineWidthPx) / 2,
                    (boxHeightPx + cursorLineHeightPx) / 2
                ),
                strokeWidth = lineHeightPx,
                alpha = cursorAlphaState
            )
        }

    }
}

sealed class VerifyType {
    object BottomLine : VerifyType()
    object Square : VerifyType()
}