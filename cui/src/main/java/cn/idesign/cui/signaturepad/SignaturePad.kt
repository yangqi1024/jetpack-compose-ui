package cn.idesign.cui.signaturepad

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Path
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.RequestDisallowInterceptTouchEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope

@Composable
fun SignaturePad(
    modifier: Modifier = Modifier,
    state: SignaturePadState = rememberSignaturePadState(),
    color: Color = Color.Black,
    stokeWidth: Dp = 4.dp,
) {
    val paint = Paint()
    paint.strokeWidth = with(LocalDensity.current) { stokeWidth.toPx() }
    paint.color = color.toArgb()
    paint.style = Paint.Style.STROKE
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeJoin = Paint.Join.ROUND
    SignaturePad(
        modifier = modifier,
        state = state,
        paint = paint
    )
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignaturePad(
    modifier: Modifier = Modifier,
    state: SignaturePadState = rememberSignaturePadState(),
    paint: Paint,
) {
    var movePath by remember { mutableStateOf<Offset?>(null) }
    val requestDisallowInterceptTouchEvent = RequestDisallowInterceptTouchEvent()
    requestDisallowInterceptTouchEvent.invoke(false)
    LaunchedEffect(paint) {
        state.paint = paint
    }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clipToBounds()
            .border(
                1.dp,
                MaterialTheme.colors.onSurface.copy(0.12f),
                RoundedCornerShape(4.dp)
            )
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        state.currentValue.moveTo(it.x, it.y)
                    },
                    onDragEnd = {
                        movePath = null
                    }
                ) { change, dragAmount ->
                    movePath = Offset(change.position.x, change.position.y)
                }
            }

            .then(modifier)

    ) {
        state.size = size
        drawIntoCanvas { canvas ->
            with(canvas.nativeCanvas) {
                movePath?.let {
                    state.currentValue.lineTo(it.x, it.y)
                    drawPath(
                        state.currentValue,
                        paint,
                    )
                }
                drawPath(
                    state.currentValue,
                    paint,
                )
            }
        }
    }
}

@Composable
fun rememberSignaturePadState(
    path: Path = Path()
): SignaturePadState = SignaturePadState(path)

@OptIn(ExperimentalMaterialApi::class)
class SignaturePadState(val path: Path) {
    private var _currentValue: Path by mutableStateOf(path)
    internal var size: Size by mutableStateOf(Size.Zero)
    internal var paint: Paint by mutableStateOf(Paint())

    val currentValue: Path
        get() = _currentValue

    fun clear() {
        _currentValue = Path()
    }

    suspend fun save(): Bitmap = coroutineScope {
        val bitmap = Bitmap.createBitmap(
            size.width.toInt(),
            size.height.toInt(),
            Bitmap.Config.ARGB_8888
        )
        val canvas = android.graphics.Canvas(bitmap)
        canvas.drawPath(
            currentValue,
            paint,
        )
        bitmap
    }
}
