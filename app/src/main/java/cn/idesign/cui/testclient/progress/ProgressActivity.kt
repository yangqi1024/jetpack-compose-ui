package cn.idesign.cui.testclient.progress

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class ProgressActivity : BaseActivity() {

    @Composable
    override fun Render() {
        ProgressTest()
    }

    override fun title(): String = "Progress示例"
}