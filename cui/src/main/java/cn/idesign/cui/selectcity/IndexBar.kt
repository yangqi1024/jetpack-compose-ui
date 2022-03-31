package cn.idesign.cui.selectcity

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*
import kotlin.math.floor

@OptIn(ExperimentalMaterialApi::class, androidx.compose.ui.ExperimentalComposeUiApi::class)
@Composable
fun IndexBar(
    modifier: Modifier = Modifier,
    state: IndexBarState = rememberIndexBarState(),
) {
    val indexArray = arrayOf(
        "A", "B", "C", "D", "E", "F", "G", "H", "I",
        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
        "W", "X", "Y", "Z", "#"
    )
    val boxHeightSize = 16.dp
    val boxWidthSize = 16.dp
    val boxHeightSizePx = with(LocalDensity.current) { boxHeightSize.toPx() }
    val sizePx = boxHeightSizePx * indexArray.size

    Box(
        modifier = Modifier
            .then(modifier)
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_MOVE -> {
                        val index = floor(
                            it.y.coerceIn(
                                0f,
                                sizePx - 1
                            ) / boxHeightSizePx
                        ).toInt()
                        state.selectIndex = indexArray[index]
                        state.isTouchDown = true
                        Log.d(
                            "IndexBar",
                            "ACTION_MOVE:${it.y},index:${index}"
                        )
                    }
                    MotionEvent.ACTION_UP -> {
                        Timer().schedule(object : TimerTask() {
                            override fun run() {
                                state.isTouchDown = false
                            }
                        }, 300)

                    }

                }
                true
            },
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            repeat(indexArray.size) { index ->
                val item = indexArray[index]
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(width = boxWidthSize, height = boxHeightSize)
                ) {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.body1.copy(fontSize = 10.sp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.high),
                    )
                }
            }
        }
        if (state.selectIndex.isNotBlank()) {
            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            x = 0,
                            y = (indexArray.indexOf(state.selectIndex) * boxHeightSizePx).toInt()
                        )
                    }
                    .size(width = boxWidthSize, height = boxHeightSize)
                    .background(
                        color = MaterialTheme.colors.primary.copy(alpha = ContentAlpha.medium),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center

            ) {
                Text(
                    text = state.selectIndex,
                    style = MaterialTheme.typography.body1.copy(fontSize = 10.sp),
                    color = MaterialTheme.colors.onPrimary.copy(alpha = ContentAlpha.high),
                )
            }
        }
    }
}


@Composable
fun rememberIndexBarState(
): IndexBarState = rememberSaveable(saver = IndexBarState.Saver) {
    IndexBarState()
}

class IndexBarState {

    private var _selectIndex: String by mutableStateOf("")
    private var _isTouchDown: Boolean by mutableStateOf(false)

    var selectIndex: String
        get() = _selectIndex
        internal set(value) {
            if (value != _selectIndex) {
                _selectIndex = value
            }
        }
    var isTouchDown: Boolean
        get() = _isTouchDown
        internal set(value) {
            if (value != _isTouchDown) {
                _isTouchDown = value
            }
        }


    companion object {
        val Saver: Saver<IndexBarState, *> = Saver(
            save = {
                it.selectIndex
            },
            restore = {
                IndexBarState(
                )
            }
        )
    }
}

