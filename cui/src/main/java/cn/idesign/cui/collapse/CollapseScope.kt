package cn.idesign.cui.collapse

import androidx.compose.runtime.Composable

interface CollapseScope {
    fun collapseItem(
        title: String,
        key: String,
        content: @Composable CollapseItemScope.() -> Unit
    ) {

    }

    fun collapseItems(
        items: List<CollapseItemModel>,
        itemContent: @Composable CollapseItemScope.(item: CollapseItemModel) -> Unit
    ) {
    }
}