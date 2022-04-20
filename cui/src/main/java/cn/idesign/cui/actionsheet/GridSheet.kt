package cn.idesign.cui.actionsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.idesign.cui.bottomsheet.BottomSheetState
import kotlinx.coroutines.launch

@Composable
fun GridSheet(
    state: BottomSheetState,
    firstLine: Array<GridSheetItem>,
    secondLine: Array<GridSheetItem>,
    textTextStyle: TextStyle = MaterialTheme.typography.body2,
    textModifier: Modifier = Modifier,
    firstLineImageModifier: Modifier = Modifier
        .size(48.dp)
        .clip(
            CircleShape
        ),
    secondLineImageModifier: Modifier = Modifier
        .size(48.dp)
        .clip(CircleShape)
        .background(color = Color.Gray.copy(alpha = ContentAlpha.disabled)),
    textColor: Color = Color.Unspecified,
    onItemClick: ((item: GridSheetItem) -> Unit)? = null
) {
    val scope = rememberCoroutineScope()
    ActionSheet(
        state = state,
        header = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "分享到", fontWeight = FontWeight.Bold)
                IconButton(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(25.dp)
                        .background(color = Color.Gray.copy(alpha = ContentAlpha.disabled)),
                    onClick = {
                        scope.launch {
                            state.hide()
                        }
                    }
                ) {
                    Icon(Icons.Default.Close, null, modifier = Modifier.size(20.dp))
                }
            }
        },
        content = {
            Column(modifier = Modifier.fillMaxWidth()) {
                LazyRow {
                    items(firstLine) { item ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .width(70.dp)
                                .padding(top = 12.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() },
                                    enabled = onItemClick != null,
                                    onClick = {
                                        onItemClick?.let { it(item) }
                                    })
                        ) {
                            Image(
                                painter = item.painter,
                                contentDescription = null,
                                modifier = firstLineImageModifier
                            )
                            Text(
                                text = item.text,
                                style = textTextStyle,
                                color = textColor.takeOrElse {
                                    MaterialTheme.colors.onSurface.copy(
                                        alpha = ContentAlpha.high
                                    )
                                },
                                modifier = Modifier
                                    .padding(top = 6.dp)
                                    .then(textModifier)
                            )
                        }
                    }
                }
                LazyRow {
                    items(secondLine) { item ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .width(70.dp)
                                .padding(vertical = 12.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() },
                                    enabled = onItemClick != null,
                                    onClick = {
                                        onItemClick?.let { it(item) }
                                    })
                        ) {
                            Image(
                                painter = item.painter,
                                contentDescription = null,
                                modifier = secondLineImageModifier
                            )
                            Text(
                                text = item.text,
                                style = textTextStyle,
                                color = textColor.takeOrElse {
                                    MaterialTheme.colors.onSurface.copy(
                                        alpha = ContentAlpha.high
                                    )
                                },
                                modifier = Modifier
                                    .padding(top = 6.dp)
                                    .then(textModifier)
                            )
                        }
                    }
                }
            }
        }
    )
}

data class GridSheetItem(
    val painter: Painter,
    val text: String,
    val tag: Any? = null,
)
