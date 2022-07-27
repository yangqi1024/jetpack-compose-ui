package cn.idesign.cui.testclient.segmented

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class SegmentedActivity : BaseActivity() {

    @Composable
    override fun Render() {
        SegmentedTest()
    }

    override fun title(): String = "Segmented示例"
}