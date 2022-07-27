package cn.idesign.cui.testclient.timeselect

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class TimeSelectActivity : BaseActivity() {

    @Composable
    override fun Render() {
        TimeSelectTest()
    }

    override fun title(): String = "TimeSelect示例"
}