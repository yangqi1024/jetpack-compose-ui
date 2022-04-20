package cn.idesign.cui.noticebar

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NoticeBar(
    text: String,
    color: Color = MaterialTheme.colors.primary,
    backgroundColor: Color = MaterialTheme.colors.surface,
    singleLine: Boolean = false,
    showClose: Boolean = false,
    scrollable: Boolean = false,
    iconSize: Dp = 16.dp,
    prefixIcon: ImageVector? = null,
    style: TextStyle = MaterialTheme.typography.body2,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {

    var show by remember {
        mutableStateOf(true)
    }
    val rememberInfiniteTransition = rememberInfiniteTransition()
    var animProgress by remember {
        mutableStateOf(0f)
    }
    if(scrollable){
        animProgress = rememberInfiniteTransition.animateFloat(
            initialValue = 0f, targetValue = -1000f, animationSpec = infiniteRepeatable(
                tween(
                    5000,
                    easing = LinearEasing
                )
            )
        ).value
    }
    Log.d("NoticeBar","animProgress:${animProgress}")


    if (show) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            prefixIcon?.let {
                Icon(
                    it,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(iconSize)
                )
            }

            Text(
                text = text,
                color = color,
                overflow = overflow,
                maxLines = if (singleLine) 1 else Int.MAX_VALUE,
                modifier = Modifier
                    .graphicsLayer{
                        translationX = animProgress
                    }
                    .weight(1f)
                    .padding(horizontal = 5.dp),
                style = style
            )
            if (showClose) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier
                        .size(iconSize)
                        .clickable {
                            show = false
                        },
                )
            }
        }
    }
}