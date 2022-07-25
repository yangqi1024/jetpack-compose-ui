package cn.idesign.cui.category

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.idesign.cui.gridcard.GridCard
import cn.idesign.cui.gridcard.GridCardModel
import cn.idesign.cui.statefullayout.StatefulLayout
import cn.idesign.cui.statefullayout.StatefulStatus
import cn.idesign.cui.statefullayout.rememberStatefulState

@SuppressLint("RememberReturnType")
@Composable
fun Category(
    modifier: Modifier = Modifier,
    menuData: List<CategoryModel>,
    panelData: List<PanelModel> = listOf(),
    state: CategoryState = rememberCategoryState(),
    selectMenuColor: Color = Color(0xFFE53E30),
    normalMenuColor: Color = MaterialTheme.colors.onSurface,
    selectMenuBackground: Color = Color(0xFFEFF4FA),
    normalMenuBackground: Color = MaterialTheme.colors.surface,
    menuHeight: Dp = 45.dp,
    onMenuClick: ((model: CategoryModel, position: Int) -> Unit)? = null,
    onItemClick: ((model: PanelItemModel, position: Int) -> Unit)? = null,
) {

    val rememberStatefulState = rememberStatefulState()
    remember(panelData) {
        rememberStatefulState.currentState =
            if (panelData.isEmpty()) StatefulStatus.Empty else StatefulStatus.Content
    }
    Row(
        modifier = Modifier
            .background(selectMenuBackground)
            .then(modifier)
    ) {
        LazyColumn(modifier = Modifier.widthIn(max = 80.dp)) {
            items(menuData.size) { position ->
                val menu = menuData[position]
                val color =
                    if (position == state.currentPosition) selectMenuBackground else normalMenuBackground
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color)
                        .height(menuHeight)
                        .clickable(
                            enabled = onMenuClick != null,
                            onClick = {
                                onMenuClick?.let { it(menu, position) }
                            }),
                    contentAlignment = Alignment.Center
                ) {
                    val color =
                        if (position == state.currentPosition) selectMenuColor else normalMenuColor
                    Text(
                        text = menu.title,
                        style = MaterialTheme.typography.body2,
                        color = color
                    )
                }
            }
        }

        StatefulLayout(state = rememberStatefulState) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(start = 8.dp),
            ) {
                items(panelData) { panelModel ->
                    panelModel.title?.let {
                        //group title
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(menuHeight), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium),
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        repeat(panelModel.children.size) {
                            val panelChildren = panelModel.children[it]
                            GridCard(
                                data = panelChildren.children.map {
                                    GridCardModel(
                                        text = it.title,
                                        iconUrl = it.icon,
                                        iconSize = DpSize(72.dp, 72.dp)
                                    )
                                },
                                title = panelChildren.title,
                                count = 3,
                                textStyle = MaterialTheme.typography.body2.copy(fontSize = 12.sp)
                            ) { _: GridCardModel, index: Int ->
                                onItemClick?.let { it(panelChildren.children[index], index) }
                            }
                        }
                    }
                }
            }
        }

    }
}

@Preview("Category")
@Composable
fun CategoryPreview() {
    val models = listOf<CategoryModel>(
        CategoryModel("图书"),
        CategoryModel("童书"),
        CategoryModel("电子书"),
        CategoryModel("手机数码"),
        CategoryModel("创意玩具"),
        CategoryModel("女装"),
        CategoryModel("食品"),
        CategoryModel("电脑办公"),
        CategoryModel("童装童鞋"),
        CategoryModel("男装"),
        CategoryModel("男女鞋"),
        CategoryModel("运动户外"),
        CategoryModel("母婴玩具"),
        CategoryModel("家用电器"),
        CategoryModel("个护清洁"),
        CategoryModel("家居家装"),
        CategoryModel("厨房用品"),
        CategoryModel("内衣配饰"),
        CategoryModel("营养保健"),
        CategoryModel("珠宝饰品"),
        CategoryModel("汽车用品"),
        CategoryModel("家居家纺"),
    )


    Surface(modifier = Modifier.fillMaxWidth()) {
        Category(menuData = models)
    }
}