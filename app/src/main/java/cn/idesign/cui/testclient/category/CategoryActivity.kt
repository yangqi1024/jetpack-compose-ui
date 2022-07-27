package cn.idesign.cui.testclient.category

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class CategoryActivity : BaseActivity() {

    @Composable
    override fun Render() {
        CategoryTest()
    }

    override fun title(): String = "Category示例"
}