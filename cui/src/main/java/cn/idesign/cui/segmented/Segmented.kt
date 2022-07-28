package cn.idesign.cui.segmented

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalAbsoluteElevation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import cn.idesign.cui.utils.calculateForegroundColor
import java.util.*
import kotlin.math.ln


@JvmName("SegmentedSimple")
@Composable
fun Segmented(
    modifier: Modifier = Modifier,
    options: List<String> = Collections.emptyList(),
    value: String,
    block: Boolean = false,
    activeTextStyle: TextStyle = MaterialTheme.typography.body2,
    normalTextStyle: TextStyle = activeTextStyle.copy(
        color = activeTextStyle.color.copy(
            ContentAlpha.medium
        )
    ),
    onChange: (value: String) -> Unit,
) {
    Segmented(
        modifier = modifier,
        options = options.map { SegmentedModel(label = it, value = it) },
        value = value,
        block = block,
        activeTextStyle = activeTextStyle,
        normalTextStyle = normalTextStyle,
        onChange = { model ->
            onChange(model.value)
        },
    )
}

@SuppressLint("MutableCollectionMutableState", "RememberReturnType")
@Composable
fun Segmented(
    modifier: Modifier = Modifier,
    options: List<SegmentedModel> = Collections.emptyList(),
    value: String,
    block: Boolean = false,
    activeTextStyle: TextStyle = MaterialTheme.typography.body2,
    normalTextStyle: TextStyle = activeTextStyle.copy(
        color = activeTextStyle.color.copy(
            ContentAlpha.medium
        )
    ),
    backgroundColor: Color = MaterialTheme.colors.surface,
    onChange: (value: SegmentedModel) -> Unit,
) {
    val selectIndex by remember(options, value) {
        derivedStateOf {
            options.indexOfFirst { model -> model.value == value }
        }
    }

    val itemRectList by remember {
        mutableStateOf<MutableList<Triple<Float, Float, Float>>>(mutableListOf())
    }

    var selectSize by remember(itemRectList, selectIndex) {
        mutableStateOf(
            if (itemRectList.isEmpty()) {
                Triple(0f, 0f, 0f)
            } else {
                itemRectList[selectIndex]
            }
        )
    }

    val selectBoxLeft: Float by animateFloatAsState(
        selectSize.third,
        animationSpec = tween(easing = FastOutSlowInEasing)
    )

    val selectBoxWidth: Float by animateFloatAsState(
        selectSize.first,
        animationSpec = tween(easing = FastOutSlowInEasing)
    )
    val selectBoxHeight: Float by remember(selectSize) {
        mutableStateOf(selectSize.second)
    }
    LaunchedEffect(Unit) {
        selectSize = itemRectList[selectIndex]

    }

    val absoluteElevation = LocalAbsoluteElevation.current + 1.dp
    val foregroundColor = calculateForegroundColor(backgroundColor, absoluteElevation)
    val _backgroundColor = foregroundColor.compositeOver(backgroundColor)
    Box(
        modifier = Modifier
            .background(_backgroundColor, RoundedCornerShape(2.dp))
            .then(if (block) Modifier.fillMaxWidth() else Modifier)
            .then(modifier),
    ) {

        Box(modifier = Modifier
            .size(with(LocalDensity.current) {
                DpSize(selectBoxWidth.toDp(), selectBoxHeight.toDp())
            })
            .padding(2.dp)
            .offset {
                IntOffset(
                    x = selectBoxLeft.toInt(),
                    y = 0
                )
            }
            .shadow(2.dp)
            .background(MaterialTheme.colors.surface, RoundedCornerShape(2.dp))
            .animateContentSize()
        ) {}
        Row(modifier = Modifier.zIndex(1f)) {
            repeat(options.size) { index ->
                val model = options[index]
                val isDisabled = model.disabled == true
                Row(
                    modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                onChange.invoke(model)
                            },
                            enabled = !isDisabled
                        )

                        .onGloballyPositioned { coordinates ->
                            if (itemRectList.size != options.size) {
                                itemRectList.add(
                                    index, Triple(
                                        coordinates.size.width.toFloat(),
                                        coordinates.size.height.toFloat(),
                                        coordinates.boundsInParent().left
                                    )
                                )
                            }
                        }
                        .padding(11.dp, 4.dp)
                        .then(if (block) Modifier.weight(1f) else Modifier),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val style = if (index == selectIndex) activeTextStyle else normalTextStyle
                    val alpha = if (isDisabled) ContentAlpha.disabled else style.color.alpha
                    Log.d("Segmented", "alpha:${alpha},index:${index}")

                    model.icon?.invoke()

                    Text(
                        text = model.label,
                        modifier = Modifier
                            .height(32.dp)
                            .wrapContentHeight(),
                        textAlign = TextAlign.Center,
                        style = style.copy(color = style.color.copy(alpha)),
                    )
                }
            }
        }

    }

}
