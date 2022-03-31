package cn.idesign.cui.actionsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun ActionSheetItem(
    text: String? = null,
    textModifier: Modifier,
    textColor: Color,
    textTextStyle: TextStyle = MaterialTheme.typography.subtitle1,
    secondaryText: String? = null,
    secondaryTextColor: Color,
    secondaryTextModifier: Modifier,
    secondaryTextTextStyle: TextStyle = MaterialTheme.typography.body2,
    modifier: Modifier,
    horizontalAlignment: Alignment.Horizontal,
    verticalArrangement: Arrangement.HorizontalOrVertical,
    onClick: (() -> Unit)? = null,
) {

    val newModifier = modifier.then(
        Modifier
            .height(56.dp)
            .fillMaxWidth()
    )

    Column(
        modifier = newModifier.clickable(
            enabled = onClick != null,
            onClick = {
                onClick?.let { it() }
            }),
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement
    ) {
        text?.let {
            Text(
                text = it,
                color = textColor.let { color ->
                    if (color.isUnspecified) {
                        MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.high)
                    }else{
                        color
                    }
                },
                modifier = textModifier,
                style = textTextStyle
            )
        }
        secondaryText?.let {
            Text(
                text = it,
                color = secondaryTextColor.let { color ->
                    if (color.isUnspecified) {
                        MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
                    }else{
                        color
                    }

                },
                modifier = secondaryTextModifier,
                style = secondaryTextTextStyle
            )
        }
    }
}