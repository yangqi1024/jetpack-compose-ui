package cn.idesign.cui.testclient.swipe

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class SwipeActivity : BaseActivity() {

    @Composable
    override fun Render() {
        SwipeTest()
    }

    override fun title(): String = "Swipe示例"
}