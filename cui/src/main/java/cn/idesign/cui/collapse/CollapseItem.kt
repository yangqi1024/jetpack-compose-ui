package cn.idesign.cui.collapse

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp

@Composable
fun CollapseItem(
    title: String,
    open: Boolean = false,
    titleModifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    onClick: ((open: Boolean) -> Unit)? = null,
    content: @Composable () -> Unit
) {

    var _open by remember(open) {
        mutableStateOf(open)
    }
    val rotate by animateFloatAsState(
        targetValue = if (_open) 90f else 0f,
        animationSpec = tween(Spring.StiffnessMediumLow.toInt())
    )

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .clickable {
                    _open = !_open
                    onClick?.let { it(_open) }
                }
                .background(color = MaterialTheme.colors.surface.copy(ContentAlpha.high))
                .padding(horizontal = 10.dp)
                .then(titleModifier),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high)
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                modifier = Modifier
                    .rotate(rotate)
                    .size(16.dp),
                tint = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
            )
        }
        Divider()
        AnimatedVisibility(
            visible = _open,
            enter = expandVertically(
                expandFrom = Alignment.Top
            ) + fadeIn(initialAlpha = 0.3f),
            exit = shrinkVertically() + fadeOut()
        ) {
            Column(
                modifier = Modifier.background(MaterialTheme.colors.surface).padding(10.dp).then(contentModifier)
            ) {
                content()
            }
        }

    }
}

class CollapseItemModel(
    val title: String,
    val key: String
)