package cn.idesign.cui.testclient.preview

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class PreviewTestActivity : BaseActivity() {

    @Composable
    override fun Render() {
        PreviewTest()
    }

    override fun title(): String = "Preview示例"
}