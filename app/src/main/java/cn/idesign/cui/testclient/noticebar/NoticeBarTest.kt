package cn.idesign.cui.testclient.noticebar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.idesign.cui.noticebar.NoticeBar

@Composable
fun NoticeBarTest() {
    LazyColumn(Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item {
            Text(
                text = "基础用法",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            NoticeBar(text = "这是 NoticeBar 通告栏")
        }

        item {
            Text(
                text = "单行用法",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            NoticeBar(text = "[单行] 这是NoticeBar通告栏，这是NoticeBar通告栏，这是NoticeBar通告栏", singleLine = true)
        }

        item {
            Text(
                text = "多行用法",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            NoticeBar(text = "[多行] 这是NoticeBar通告栏，这是NoticeBar通告栏，这是NoticeBar通告栏")
        }

        item {
            Text(
                text = "前缀图标",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            NoticeBar(text = "这是 NoticeBar 通告栏", prefixIcon = Icons.Default.Notifications)
        }

        item {
            Text(
                text = "关闭图标",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            NoticeBar(
                text = "这是 NoticeBar 通告栏",
                prefixIcon = Icons.Default.Notifications,
                showClose = true
            )
        }

        item {
            Text(
                text = "自定义样式",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            NoticeBar(
                text = "这是 NoticeBar 通告栏",
                prefixIcon = Icons.Default.Notifications,
                showClose = true,
                color = MaterialTheme.colors.secondary
            )
        }

//        item {
//
//            Text(text = "文字滚动")
//            NoticeBar(
//                text = "这是 NoticeBar 通告栏",
//                prefixIcon = Icons.Default.Notifications,
//                showClose = true,
//                scrollable = true,
//                color = MaterialTheme.colors.primary.copy(ContentAlpha.medium)
//            )
//        }
    }
}