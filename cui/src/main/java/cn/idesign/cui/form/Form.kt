package cn.idesign.cui.form

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("RememberReturnType")
@Composable
fun Form(
    state: FormState = rememberFormState(),
    onFinish: ((Map<String, Any>) -> Unit)? = null,
    builder: FormBuilder.() -> Unit,
) {

    Column() {
        FormBuilder().apply(builder).formItems.forEach { item ->
            val itemState = item.state ?: rememberFormItemState()
            remember(itemState.value) {
                println("---> itemState  key:${item.name},value:${itemState.value}")
                item.name?.let {
                    state.value.put(it, itemState.value)
                }
            }
            FormField(
                label = item.label,
                state = itemState,
                showDivider = item.type === FormItemType.Normal
            ) {
                item.content(itemState)
            }
        }
    }
}

@Composable
fun FormField(
    modifier: Modifier = Modifier,
    label: String? = null,
    labelAlign: TextAlign = TextAlign.Left,
    labelWeight: Float = 1f,
    wrapperWeight: Float = 3f,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    showDivider: Boolean = true,
    state: FormItemState,
    content: @Composable() () -> Unit
) {
    println("FormField state:$state")
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .then(modifier),
            verticalAlignment = verticalAlignment,
        ) {
            label?.let {
                Text(
                    text = label,
                    modifier = Modifier.weight(labelWeight),
                    textAlign = labelAlign
                )
            }
            Box(modifier = Modifier.weight(wrapperWeight)) {
                content.invoke()
            }
        }
        if (showDivider) {
            Divider()
        }
    }
}

@Composable
fun FormInput(
    state: FormItemState,
    placeholder: String = "请输入搜索信息",
    color: Color = MaterialTheme.colors.onSurface.copy(ContentAlpha.disabled),
) {
    BasicTextField(
        value = state.value,
        onValueChange = {
            state.value = it
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        textStyle = MaterialTheme.typography.body2,
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions.Default,

        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                if (state.value.isEmpty()) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = placeholder,
                        color = color,
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview("form")
@Composable
fun FormPreview() {
    Form() {
        composable(
            label = "姓名",
            name = "username",
        ) {
            FormInput(state = it)
        }
        composable(
            label = "手机号",
            name = "mobile",
        ) {
            FormInput(state = it)
        }
    }
}