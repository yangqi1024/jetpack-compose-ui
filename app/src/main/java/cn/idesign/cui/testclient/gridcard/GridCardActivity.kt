package cn.idesign.cui.testclient.gridcard

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class GridCardActivity : BaseActivity() {

    @Composable
    override fun Render() {
        GridCardTest()
    }

    override fun title(): String = "GridCard示例"
}