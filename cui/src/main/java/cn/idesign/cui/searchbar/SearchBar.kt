package cn.idesign.cui.testclient.searchbar

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.MotionLayout

@SuppressLint("Range")
@OptIn(
    ExperimentalComposeUiApi::class,
    androidx.constraintlayout.compose.ExperimentalMotionApi::class
)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    actionText: String = "搜索",
    value: String,
    placeholder: String = "请输入搜索信息",
    showAction: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    color: Color = MaterialTheme.colors.onSurface.copy(ContentAlpha.disabled),
    onActionClick: ((value: String) -> Unit)? = null,
    onChange: ((value: String) -> Unit)? = null,
) {
    var focus by remember {
        mutableStateOf(false)
    }
    val progress by animateFloatAsState(
        targetValue = if (focus) 1f else 0f,
        animationSpec = tween(300)
    )

    val startSet = ConstraintSet {
        val background = createRefFor("background")
        val action = createRefFor("action")
        val search = createRefFor("search")
        val input = createRefFor("input")
        val close = createRefFor("close")

        constrain(background) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            start.linkTo(parent.start)
            end.linkTo(action.start, margin = 8.dp)
            centerVerticallyTo(parent)
        }
        constrain(action) {
            if (showAction) {
                alpha = 1f
                end.linkTo(parent.end)
            } else {
                start.linkTo(parent.end)
                alpha = 0f
            }
            centerVerticallyTo(background)
        }
        constrain(search) {
            start.linkTo(background.start, margin = 8.dp)
            end.linkTo(input.start)
            centerVerticallyTo(background)
        }
        constrain(input) {
            width = Dimension.percent(0.5f)
            start.linkTo(search.end, margin = 8.dp)
            centerVerticallyTo(background)
        }
        constrain(close) {
            end.linkTo(background.end, margin = 8.dp)
            centerVerticallyTo(background)
        }
        createHorizontalChain(search, input, chainStyle = ChainStyle.Packed)
    }

    val endSet = ConstraintSet {
        val background = createRefFor("background")
        val action = createRefFor("action")
        val search = createRefFor("search")
        val input = createRefFor("input")
        val close = createRefFor("close")
        constrain(background) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            start.linkTo(parent.start)
            end.linkTo(action.start, margin = 8.dp)
            centerVerticallyTo(parent)
        }
        constrain(action) {
            alpha = 1f
            end.linkTo(parent.end)
            centerVerticallyTo(background)
        }
        constrain(search) {
            start.linkTo(background.start, margin = 8.dp)
            centerVerticallyTo(background)
        }
        constrain(input) {
            start.linkTo(search.end, margin = 8.dp)
            end.linkTo(close.start, margin = 8.dp)
            width = Dimension.fillToConstraints
            centerVerticallyTo(background)
        }
        constrain(close) {
            end.linkTo(background.end, margin = 8.dp)
            centerVerticallyTo(background)
        }
    }
    MotionLayout(
        start = startSet, end = endSet, progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .then(modifier),
    ) {
        Box(
            modifier = Modifier
                .layoutId("background")
                .background(MaterialTheme.colors.onSurface.copy(0.05f), MaterialTheme.shapes.small)
        )
        Icon(
            Icons.Default.Search,
            null,
            modifier = Modifier
                .layoutId("search")
                .size(20.dp),
            tint = color
        )
        BasicTextField(
            value = value,
            onValueChange = { value ->
                onChange?.let { it(value) }
            },
            singleLine = true,
            modifier = Modifier
                .layoutId("input")
                .onFocusChanged { focus = it.isFocused },
            textStyle = MaterialTheme.typography.body2,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = color,
                        )
                    }
                }
                innerTextField()
            }
        )
        AnimatedVisibility(
            modifier = Modifier
                .layoutId("close"),
            visible = value.isNotBlank(), enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Icon(
                Icons.Default.Close,
                null,
                tint = MaterialTheme.colors.onSurface.copy(ContentAlpha.disabled),
                modifier = Modifier
                    .size(20.dp)
                    .padding(2.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            onChange?.let { it("") }
                        }
                    )
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.onSurface.copy(ContentAlpha.disabled),
                        shape = CircleShape
                    )
            )
        }

        Button(
            onClick = { onActionClick?.let { it(value) } },
            modifier = Modifier
                .layoutId("action")
        ) {
            Text(text = actionText)
        }

    }

}