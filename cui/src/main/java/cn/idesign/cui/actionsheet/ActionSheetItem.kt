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
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun ActionSheetItem(
    modifier: Modifier,
    text: String? = null,
    textModifier: Modifier,
    textColor: Color,
    textTextStyle: TextStyle = MaterialTheme.typography.subtitle1,
    secondaryText: String? = null,
    secondaryTextColor: Color,
    secondaryTextModifier: Modifier,
    secondaryTextTextStyle: TextStyle = MaterialTheme.typography.body2,
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
                color = textColor.takeOrElse { MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.high) },
                modifier = textModifier,
                style = textTextStyle
            )
        }
        secondaryText?.let {
            Text(
                text = it,
                color = secondaryTextColor.takeOrElse { MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled) },
                modifier = secondaryTextModifier,
                style = secondaryTextTextStyle
            )
        }
    }
}