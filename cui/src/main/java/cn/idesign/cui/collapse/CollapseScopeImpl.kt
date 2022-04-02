package cn.idesign.cui.collapse

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable

@SuppressLint("MutableCollectionMutableState")
class CollapseScopeImpl : CollapseScope {
    private val _intervals = mutableListOf<CollapseIntervalContent>()
    val intervals: List<CollapseIntervalContent> = _intervals
    override fun collapseItem(title: String, key: String, content: @Composable CollapseItemScope.() -> Unit) {
        _intervals.add(CollapseIntervalContent(
            key = key,
            title = title,
            content = { @Composable {content()} }
        ))
    }

    override fun collapseItems(
        items: List<CollapseItemModel>,
        itemContent: @Composable (CollapseItemScope.(item: CollapseItemModel) -> Unit)
    ) {
        repeat(items.size) { index ->
            _intervals.add(
                CollapseIntervalContent(
                    key = items[index].title,
                    title = items[index].title,
                    content = { @Composable { itemContent(items[index]) } }
                )
            )
        }

    }


}


class CollapseIntervalContent(
    val key: String,
    val title: String,
    val content: @Composable CollapseItemScope.() -> @Composable () -> Unit
)