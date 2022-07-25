package cn.idesign.cui.gridcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cn.idesign.cui.image.NetworkImage
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun GridCard(
    modifier: Modifier = Modifier,
    title: String? = null,
    titleStyle: TextStyle = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Medium),
    textStyle: TextStyle = MaterialTheme.typography.body2,
    count: Int = 4,
    data: List<GridCardModel> = listOf(),
    horizontalSpacing: Dp = 0.dp,
    verticalSpacing: Dp = 0.dp,
    onItemClick: ((value: GridCardModel, index: Int) -> Unit)? = null,
) {
    BoxWithConstraints(modifier) {
        val itemWidthDp = (maxWidth - horizontalSpacing * count) / count
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .then(modifier)
        ) {
            title?.let {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 5.dp),
                ) {
                    Text(
                        text = it,
                        style = titleStyle
                    )
                }
            }

            FlowRow(
                mainAxisSpacing = horizontalSpacing,
                crossAxisSpacing = verticalSpacing
            ) {
                repeat(data.size) { index ->
                    val model = data[index]
                    Column(
                        Modifier
                            .width(itemWidthDp)
                            .padding(bottom = 10.dp)
                            .clickable(indication = null,
                                interactionSource = remember { MutableInteractionSource() },
                                enabled = onItemClick != null,
                                onClick = {
                                    onItemClick?.invoke(model, index)
                                }),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        model.iconPainter?.let {
                            Image(
                                painter = it,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(vertical = 5.dp)
                                    .size(model.iconSize)
                            )
                        }
                        model.iconUrl?.let {
                            NetworkImage(
                                data = it,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(vertical = 5.dp)
                                    .size(model.iconSize)
                            )
                        }
                        model.text?.let {
                            Text(
                                text = it,
                                modifier = Modifier.padding(top = 6.dp),
                                color = model.textColor.takeOrElse {
                                    MaterialTheme.colors.onSurface.copy(
                                        ContentAlpha.medium
                                    )
                                },
                                style = textStyle
                            )
                        }
                    }
                }

            }
        }
    }

}