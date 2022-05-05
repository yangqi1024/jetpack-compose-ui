package cn.idesign.cui.testclient.skeleton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.idesign.cui.image.NetworkImage
import cn.idesign.cui.skeleton.Skeleton
import cn.idesign.cui.skeleton.SkeletonImage
import cn.idesign.cui.skeleton.SkeletonParagraph

@Composable
fun SkeletonTest() {
    var loading by remember {
        mutableStateOf(true)
    }
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
            Skeleton()
        }
        item {
            Text(
                text = "复杂组合",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Skeleton(avatar = true, rows = 4)
        }

        item {
            Text(
                text = "动画效果",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Skeleton(avatar = true, rows = 4, animated = true)
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
            Skeleton(defaultContent = {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    SkeletonImage(animated = true, modifier = Modifier.size(100.dp))
                    SkeletonParagraph(animated = true)
                }
            })
        }
        item {
            Text(
                text = "Loading状态",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )

            Column() {
                Switch(checked = loading, onCheckedChange = { loading = it })
                Skeleton(loading = loading, defaultContent = {
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        SkeletonImage(animated = true, modifier = Modifier.size(100.dp))
                        SkeletonParagraph(animated = true)
                    }
                }) {
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        NetworkImage(
                            data = "https://image.uisdc.com/wp-content/uploads/2018/12/uisdc-jl-20181224-36.jpg",
                            modifier = Modifier.size(100.dp)
                        )
                        Column {
                            Text(text = "标题")
                            Text(text = "这是一个描述")
                        }
                    }
                }
            }
        }
    }
}