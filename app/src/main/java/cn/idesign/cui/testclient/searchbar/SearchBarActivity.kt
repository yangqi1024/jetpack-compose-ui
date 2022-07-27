package cn.idesign.cui.testclient.searchbar

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class SearchBarActivity : BaseActivity() {

    @Composable
    override fun Render() {
        SearchBarTest()
    }

    override fun title(): String = "SearchBar示例"
}