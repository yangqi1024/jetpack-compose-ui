package cn.idesign.cui.testclient.statefullayout

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class StatefulLayoutActivity : BaseActivity() {

    @Composable
    override fun Render() {
        StatefulLayoutTest()
    }

    override fun title(): String = "StatefulLayout示例"
}