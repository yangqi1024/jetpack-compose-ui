package cn.idesign.cui.testclient.steps

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class StepsActivity : BaseActivity() {

    @Composable
    override fun Render() {
        StepsTest()
    }

    override fun title(): String = "Steps示例"
}