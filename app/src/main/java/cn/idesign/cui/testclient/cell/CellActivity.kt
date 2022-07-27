package cn.idesign.cui.testclient.cell

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class CellActivity : BaseActivity() {

    @Composable
    override fun Render() {
        CellTest()
    }

    override fun title(): String = "Cell示例"
}