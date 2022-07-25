package cn.idesign.cui.testclient.category

import androidx.compose.runtime.Composable
import cn.idesign.cui.category.Category
import cn.idesign.cui.category.CategoryModel
import cn.idesign.cui.category.PanelGroup
import cn.idesign.cui.category.PanelItemModel
import cn.idesign.cui.category.PanelModel
import cn.idesign.cui.category.rememberCategoryState

@Composable
fun CategoryTest() {
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
    val commonData = listOf(
        PanelModel(
            children = listOf(
                PanelGroup(
                    title = "热门品牌",
                    children = listOf(
                        PanelItemModel(
                            title = "三只松鼠",
                            icon = "https://img60.ddimg.cn/upload_img/00404/fengyingrb/songshu.png"
                        ),
                        PanelItemModel(
                            title = "百草味",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/baicaowei.png"
                        ),
                        PanelItemModel(
                            title = "良品铺子",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/liangpin.png"
                        ),
                        PanelItemModel(
                            title = "三只松鼠1",
                            icon = "https://img60.ddimg.cn/upload_img/00404/fengyingrb/songshu.png"
                        ),
                        PanelItemModel(
                            title = "百草味1",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/baicaowei.png"
                        ),
                        PanelItemModel(
                            title = "良品铺子1",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/liangpin.png"
                        ),
                        PanelItemModel(
                            title = "三只松鼠2",
                            icon = "https://img60.ddimg.cn/upload_img/00404/fengyingrb/songshu.png"
                        ),
                        PanelItemModel(
                            title = "百草味2",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/baicaowei.png"
                        ),
                        PanelItemModel(
                            title = "良品铺子2",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/liangpin.png"
                        ),
                        PanelItemModel(
                            title = "三只松鼠3",
                            icon = "https://img60.ddimg.cn/upload_img/00404/fengyingrb/songshu.png"
                        ),
                        PanelItemModel(
                            title = "百草味4",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/baicaowei.png"
                        ),
                        PanelItemModel(
                            title = "良品铺子5",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/liangpin.png"
                        )
                    )
                )
            )
        ),
        PanelModel(
            "食品畅销榜",
            children = listOf(
                PanelGroup(
                    title = "休闲零食",
                    children = listOf(
                        PanelItemModel(
                            title = "三只松鼠",
                            icon = "https://img60.ddimg.cn/upload_img/00404/fengyingrb/songshu.png"
                        ),
                        PanelItemModel(
                            title = "百草味",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/baicaowei.png"
                        ),
                        PanelItemModel(
                            title = "良品铺子",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/liangpin.png"
                        ),
                        PanelItemModel(
                            title = "三只松鼠1",
                            icon = "https://img60.ddimg.cn/upload_img/00404/fengyingrb/songshu.png"
                        ),
                        PanelItemModel(
                            title = "百草味1",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/baicaowei.png"
                        ),
                        PanelItemModel(
                            title = "良品铺子1",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/liangpin.png"
                        ),
                        PanelItemModel(
                            title = "三只松鼠2",
                            icon = "https://img60.ddimg.cn/upload_img/00404/fengyingrb/songshu.png"
                        ),
                        PanelItemModel(
                            title = "百草味2",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/baicaowei.png"
                        ),
                        PanelItemModel(
                            title = "良品铺子2",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/liangpin.png"
                        ),
                        PanelItemModel(
                            title = "三只松鼠3",
                            icon = "https://img60.ddimg.cn/upload_img/00404/fengyingrb/songshu.png"
                        ),
                        PanelItemModel(
                            title = "百草味4",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/baicaowei.png"
                        ),
                        PanelItemModel(
                            title = "良品铺子5",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/liangpin.png"
                        )
                    )
                ),
                PanelGroup(
                    title = "爆款零食",
                    children = listOf(
                        PanelItemModel(
                            title = "三只松鼠",
                            icon = "https://img60.ddimg.cn/upload_img/00404/fengyingrb/songshu.png"
                        ),
                        PanelItemModel(
                            title = "百草味",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/baicaowei.png"
                        ),
                        PanelItemModel(
                            title = "良品铺子",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/liangpin.png"
                        ),
                        PanelItemModel(
                            title = "三只松鼠1",
                            icon = "https://img60.ddimg.cn/upload_img/00404/fengyingrb/songshu.png"
                        ),
                        PanelItemModel(
                            title = "百草味1",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/baicaowei.png"
                        ),
                        PanelItemModel(
                            title = "良品铺子1",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/liangpin.png"
                        ),
                        PanelItemModel(
                            title = "三只松鼠2",
                            icon = "https://img60.ddimg.cn/upload_img/00404/fengyingrb/songshu.png"
                        ),
                        PanelItemModel(
                            title = "百草味2",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/baicaowei.png"
                        ),
                        PanelItemModel(
                            title = "良品铺子2",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/liangpin.png"
                        ),
                        PanelItemModel(
                            title = "三只松鼠3",
                            icon = "https://img60.ddimg.cn/upload_img/00404/fengyingrb/songshu.png"
                        ),
                        PanelItemModel(
                            title = "百草味4",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/baicaowei.png"
                        ),
                        PanelItemModel(
                            title = "良品铺子5",
                            icon = "https://img62.ddimg.cn/upload_img/00404/fengyingrb/liangpin.png"
                        )
                    )
                )
            )
        ),
    )
    val data = mutableListOf<List<PanelModel>>()
    repeat(models.size) {
        if (it % 2 == 0) {
            data.add(commonData)
        } else {
            data.add(listOf())
        }
    }
    val state = rememberCategoryState()
    Category(
        menuData = models,
        panelData = data[state.currentPosition],
        state = state,
        onMenuClick = { model: CategoryModel, position: Int ->
            state.currentPosition = position
        },
        onItemClick = { model: PanelItemModel, position: Int ->
            println("model:${model}")
        })

}