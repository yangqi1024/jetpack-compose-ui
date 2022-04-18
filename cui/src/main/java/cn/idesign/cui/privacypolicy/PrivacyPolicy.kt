package cn.idesign.cui.privacypolicy

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import cn.idesign.cui.modal.Modal
import cn.idesign.cui.modal.ModalState
import cn.idesign.cui.modal.rememberModalState
import kotlinx.coroutines.launch
import java.util.regex.Pattern

/**
 * 用户协议和隐私政策组件
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PrivacyPolicy(
    modifier: Modifier = Modifier,
    state: ModalState = rememberModalState(),
    text: String,
    textColor: Color = MaterialTheme.colors.onSurface,
    textTextStyle: TextStyle = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium),
    secondaryText: String,
    secondaryTextColor: Color = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium),
    secondaryTextStyle: TextStyle = MaterialTheme.typography.body2.copy(
        fontSize = 12.sp,
        color = secondaryTextColor
    ),
    secondaryTextTagList: List<String> = listOf(),
    tagStyle: SpanStyle = SpanStyle(color = MaterialTheme.colors.primary),
    okText: String = "同意",
    okButtonColors: ButtonColors = ButtonDefaults.buttonColors(),
    cancelText: String = "不同意",
    okTextStyle: TextStyle = secondaryTextStyle.copy(color = MaterialTheme.colors.surface),
    onOkClick: (() -> Unit)? = null,
    onCancelClick: (() -> Unit)? = null,
    onTagClick: ((tag: String) -> Unit)? = null,
) {
    val scope = rememberCoroutineScope()
    val okClickState = rememberUpdatedState(onOkClick)
    val cancelClickState = rememberUpdatedState(onCancelClick)
    val tagClickState = rememberUpdatedState(onTagClick)
    val pattern = Pattern.compile(secondaryTextTagList.joinToString("|"))
    val annotationText = buildAnnotatedString {
        val matcher = pattern.matcher(secondaryText)
        var startIndex = 0
        while (matcher.find()) {
            append(secondaryText.subSequence(startIndex, matcher.start()).toString())
            pushStringAnnotation(
                tag = matcher.group(),
                annotation = matcher.group()
            )
            withStyle(
                style = tagStyle
            ) {
                append(matcher.group())
            }
            startIndex = matcher.end()
            pop()
            Log.d(
                "PrivacyPolicy",
                "matcher:${matcher.group()},start:${matcher.start()},end:${matcher.end()}"
            )
        }
        if (startIndex < secondaryText.length) {
            append(secondaryText.subSequence(startIndex, secondaryText.length).toString())
        }
    }
    Modal(
        state = state,
        onClose = {
            scope.launch {
                state.hide()
            }
        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .background(color = MaterialTheme.colors.surface, RoundedCornerShape(10.dp))
                .padding(20.dp, 20.dp, 20.dp, 10.dp)
                .then(modifier),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                color = textColor,
                style = textTextStyle,
                modifier = Modifier.padding(bottom = 15.dp)
            )

            ClickableText(
                modifier = Modifier
                    .height(175.dp)
                    .verticalScroll(state = rememberScrollState())
                    .padding(bottom = 15.dp),
                text = annotationText,
                style = secondaryTextStyle,
                onClick = { offset ->
                    secondaryTextTagList.forEach { tag ->
                        annotationText.getStringAnnotations(
                            tag = tag, start = offset,
                            end = offset
                        ).firstOrNull()?.let { annotation ->
                            tagClickState.value?.invoke(annotation.item)
                        }
                    }
                }
            )
            Button(
                onClick = { okClickState.value?.invoke() },
                modifier = Modifier.fillMaxWidth(),
                colors = okButtonColors,
            ) {
                Text(text = okText, style = okTextStyle)
            }
            Text(
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        enabled = cancelClickState.value != null,
                        onClick = {
                            cancelClickState.value?.invoke()
                        }),
                text = cancelText,
                color = textColor.copy(alpha = ContentAlpha.disabled),
                style = secondaryTextStyle, textAlign = TextAlign.Center
            )
        }

    }

}