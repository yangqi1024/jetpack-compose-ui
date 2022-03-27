package cn.idesign.cui.actionsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun GridSheet(
    state: ActionSheetState,
    firstLine: Array<GridSheetItem>,
    secondLine: Array<GridSheetItem>,
    onItemClick: ((item: GridSheetItem) -> Unit)? = null
) {
    ActionSheet(
        state = state,
        content = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row {
                    repeat(firstLine.size) {
                        val item = firstLine[it]
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(4.dp)
                                .clickable(
                                    enabled = onItemClick != null,
                                    onClick = {
                                        onItemClick?.let { it(item) }
                                    })
                        ) {
                            Image(painter = item.painter, contentDescription = null)
                            Text(text = item.title, style = MaterialTheme.typography.body2)
                        }
                    }
                }
                Row {
                    repeat(secondLine.size) {
                        val item = secondLine[it]
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(4.dp)
                                .clickable(
                                    enabled = onItemClick != null,
                                    onClick = {
                                        onItemClick?.let { it(item) }
                                    })
                        ) {
                            Image(painter = item.painter, contentDescription = null)
                            Text(text = item.title, style = MaterialTheme.typography.body2)
                        }
                    }
                }
            }
        }
    )
}

data class GridSheetItem(
    val painter: Painter,
    val title: String,
    val tag: Int? = null,
)
