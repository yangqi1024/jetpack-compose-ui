package cn.idesign.cui.testclient.banner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cn.idesign.cui.banner.Banner
import cn.idesign.cui.banner.BannerSampleItem
import cn.idesign.cui.testclient.R
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerTest() {
    val contentPadding = remember {
        mutableStateOf(0f)
    }
    val itemSpacing = remember {
        mutableStateOf(0f)
    }
    val dataList = arrayListOf<BannerItem>(
        BannerItem(painterResource(id = R.drawable.image1),"相信自己,你努力的样子真的很美"),
        BannerItem(painterResource(id = R.drawable.image2),"极致简约,梦幻小屋"),
        BannerItem(painterResource(id = R.drawable.image3),"超级卖梦人"),
        BannerItem(painterResource(id = R.drawable.image4),"夏季新搭配"),
        BannerItem(painterResource(id = R.drawable.image5),"绝美风格搭配"),
        BannerItem(painterResource(id = R.drawable.image6),"微微一笑 很倾城"),
    )
    Column( modifier = Modifier.background(color =  MaterialTheme.colors.background)) {
        Banner(
            count = dataList.size,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.height(180.dp),
            itemSpacing = itemSpacing.value.dp,
            contentPadding = PaddingValues(all = contentPadding.value.dp),
        ) { page ->
            BannerSampleItem(
                page = page,
                data = dataList.get(page),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }

        Column() {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "内容Padding:${Math.round(contentPadding.value)} dp" ,modifier = Modifier.width(150.dp))
                Spacer(modifier = Modifier.width(5.dp))
                Slider(value = contentPadding.value, onValueChange = { contentPadding.value = it } ,valueRange = 0f..100f)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "内容间距:${Math.round(itemSpacing.value) } dp",modifier = Modifier.width(150.dp))
                Spacer(modifier = Modifier.width(5.dp))
                Slider(value = itemSpacing.value, onValueChange = { itemSpacing.value = it } ,valueRange = 0f..100f)
            }
        }

    }
}
