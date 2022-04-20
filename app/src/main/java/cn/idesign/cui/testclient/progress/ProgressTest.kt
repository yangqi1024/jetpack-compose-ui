package cn.idesign.cui.testclient.progress

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.idesign.cui.progress.CircleProgress

@SuppressLint("UnrememberedMutableState")
@Composable
fun ProgressTest() {
    var progress by mutableStateOf(0f)
    LazyColumn(Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item {
            Text(
                text = "圆形进度条",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            CircleProgress(
                progress = progress,
                modifier = Modifier.size(60.dp),
            )
        }
        item {
            Text(
                text = "自定义颜色",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                CircleProgress(
                    progress = progress,
                    modifier = Modifier.size(60.dp),
                    color = MaterialTheme.colors.secondary
                )
                CircleProgress(
                    progress = progress,
                    modifier = Modifier.size(60.dp),
                    color = MaterialTheme.colors.secondary,
                    backgroundColor = MaterialTheme.colors.primary.copy(alpha = ProgressIndicatorDefaults.IndicatorBackgroundOpacity)
                )
            }
        }
        item {
            Text(
                text = "自定义宽度",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            CircleProgress(
                progress = progress,
                modifier = Modifier.size(60.dp),
                strokeWidth = 8.dp,
            )
        }
        item {
            Text(
                text = "自定义形状",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                CircleProgress(
                    progress = progress,
                    modifier = Modifier.size(60.dp),
                    strokeWidth = 8.dp,
                    strokeCap = StrokeCap.Butt
                )
            }
        }
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { progress = 0f }) {
                    Text(text = "回滚")
                }
                Button(onClick = { progress = 1f }) {
                    Text(text = "开始")
                }
            }
        }
    }
}