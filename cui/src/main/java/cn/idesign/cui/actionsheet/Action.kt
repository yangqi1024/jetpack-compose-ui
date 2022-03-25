package cn.idesign.cui.actionsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class Action(
    val text: String? = null,
    val textModifier: Modifier = Modifier,
    val textColor: Color = Color.Unspecified,
    val secondaryText: String? = null,
    val secondaryTextColor: Color = Color.Unspecified,
    val secondaryTextModifier: Modifier = Modifier.padding(top = 4.dp),
    val modifier: Modifier = Modifier,
    val horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    val verticalArrangement: Arrangement.HorizontalOrVertical = Arrangement.Center,
)
