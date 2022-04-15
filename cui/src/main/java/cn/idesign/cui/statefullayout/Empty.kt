package cn.idesign.cui.statefullayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun Empty(
    modifier: Modifier = Modifier,
    text: String = "暂无数据",
    textStyle: TextStyle = MaterialTheme.typography.body2,
    textColor: Color = MaterialTheme.colors.onSurface.copy(ContentAlpha.disabled),
    image:@Composable ()->Unit = {
        Icon(imageVector = Icons.Default.FolderOpen, contentDescription = null,modifier = Modifier.size(40.dp),tint = textColor)
    }
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        image()
        Text(
            text = text,
            style = textStyle,
            color = textColor,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}