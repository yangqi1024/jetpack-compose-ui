package cn.idesign.cui.testclient.searchbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    actionText: String = "搜索",
    value: String,
    placeholder: String = "请输入搜索信息",
    modifier: Modifier = Modifier,
    showAction: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    color: Color = MaterialTheme.colors.onSurface.copy(ContentAlpha.disabled),
    onActionClick: (() -> Unit)? = null,
    onChange: ((value: String) -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.onSurface.copy(0.05f), MaterialTheme.shapes.small)
                .padding(8.dp)
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Search,
                null,
                modifier = Modifier
                    .size(20.dp),
                tint = color
            )
            Spacer(modifier = Modifier.width(10.dp))
            BasicTextField(
                value = value,
                onValueChange = { value ->
                    onChange?.let { it(value) }
                },
                singleLine = true,
                modifier = Modifier.weight(1f),
                textStyle = MaterialTheme.typography.body2,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                decorationBox = { innerTextField ->
                    Row(modifier = Modifier.fillMaxWidth()) {
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
            Spacer(modifier = Modifier.width(10.dp))
            AnimatedVisibility(
                visible = value.isNotBlank(), enter = fadeIn(),
                exit = fadeOut(),
            ) {
                Icon(
                    Icons.Default.Close,
                    null,
                    tint = MaterialTheme.colors.surface.copy(ContentAlpha.high),
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
                        .background(
                            MaterialTheme.colors.onSurface.copy(0.12f),
                            CircleShape
                        )
                )
            }
        }
        if (showAction) {
            Button(
                onClick = { onActionClick?.let { it() } },
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(text = actionText)
            }
        }

    }
}