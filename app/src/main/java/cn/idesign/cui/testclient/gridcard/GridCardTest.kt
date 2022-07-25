package cn.idesign.cui.testclient.gridcard

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.idesign.cui.gridcard.GridCard
import cn.idesign.cui.gridcard.GridCardModel
import cn.idesign.cui.testclient.R

@Composable
fun GridCardTest() {
    val context = LocalContext.current
    val commonData = listOf(
        GridCardModel(
            "我的活动",
            iconPainter = painterResource(id = R.drawable.acivity)
        ),
        GridCardModel(
            "我的相册",
            iconPainter = painterResource(id = R.drawable.album)
        ),
        GridCardModel(
            "我的徽章",
            iconPainter = painterResource(id = R.drawable.badge)
        ),
        GridCardModel(
            "我的收藏",
            iconPainter = painterResource(id = R.drawable.college)
        ),
        GridCardModel(
            "优惠券",
            iconPainter = painterResource(id = R.drawable.coupon)
        ),
        GridCardModel(
            "消息",
            iconPainter = painterResource(id = R.drawable.news)
        )

    )
    LazyColumn(Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item {
            Text(
                text = "基本用法",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            GridCard(data = commonData)
        }
        item {
            Text(
                text = "显示标题",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            GridCard(data = commonData, title = "我的收藏")
        }
        item {
            Text(
                text = "自定义一行数量",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            GridCard(data = commonData, title = "我的收藏", count = 3)
        }

        item {
            Text(
                text = "点击事件",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            GridCard(
                data = commonData,
                title = "我的收藏",
                count = 3,
                onItemClick = { model: GridCardModel, _: Int ->
                    Toast.makeText(context, "点击了：${model.text}", Toast.LENGTH_SHORT).show()
                })
        }
    }
}