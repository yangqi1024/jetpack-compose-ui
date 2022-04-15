package cn.idesign.cui.statefullayout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun Loading(
    modifier: Modifier = Modifier,
    text: String = "加载中",
    textStyle: TextStyle = MaterialTheme.typography.body2,
    textColor: Color = MaterialTheme.colors.onSurface.copy(ContentAlpha.disabled),
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(24.dp),strokeWidth = 2.dp)
        Text(
            text = text,
            style = textStyle,
            color = textColor,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}