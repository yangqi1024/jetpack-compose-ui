package cn.idesign.cui.testclient.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import cn.idesign.cui.image.NetworkImage
import cn.idesign.cui.testclient.R

@Composable
fun NetworkImageTest() {
    val context = LocalContext.current
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
            NetworkImage(
                data = "https://img01.yzcdn.cn/vant/cat.jpeg", modifier = Modifier
                    .size(100.dp)
            )
        }


        item {
            Text(
                text = "填充模式",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Column() {
                    NetworkImage(
                        data = "https://img01.yzcdn.cn/vant/cat.jpeg",
                        modifier = Modifier
                            .size(100.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "Crop",
                        color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high)
                    )
                }
                Column() {
                    NetworkImage(
                        data = "https://img01.yzcdn.cn/vant/cat.jpeg",
                        modifier = Modifier
                            .size(100.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = "Fit",
                        color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high)
                    )
                }
                Column() {
                    NetworkImage(
                        data = "https://img01.yzcdn.cn/vant/cat.jpeg",
                        modifier = Modifier
                            .size(100.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = "FillBounds",
                        color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high)
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Column() {
                    NetworkImage(
                        data = "https://img01.yzcdn.cn/vant/cat.jpeg",
                        modifier = Modifier
                            .size(100.dp),
                        contentScale = ContentScale.FillHeight
                    )
                    Text(
                        text = "FillHeight",
                        color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high)
                    )
                }
                Column() {
                    NetworkImage(
                        data = "https://img01.yzcdn.cn/vant/cat.jpeg",
                        modifier = Modifier
                            .size(100.dp),
                        contentScale = ContentScale.FillWidth
                    )
                    Text(
                        text = "FillWidth",
                        color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high)
                    )
                }
                Column() {
                    NetworkImage(
                        data = "https://img01.yzcdn.cn/vant/cat.jpeg",
                        modifier = Modifier
                            .size(100.dp),
                        contentScale = ContentScale.Inside
                    )
                    Text(
                        text = "Inside",
                        color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high)
                    )
                }
            }
        }

        item {
            Text(
                text = "加载失败",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            NetworkImage(
                data = "https://img01.yzcdn.cn/vant/cat1.jpeg", modifier = Modifier
                    .size(100.dp),
                errorDrawable = ContextCompat.getDrawable(
                    context,
                    R.drawable.icon_more_operation_save
                )
            )
        }
    }
}