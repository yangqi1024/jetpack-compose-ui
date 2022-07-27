package cn.idesign.cui.testclient.rate

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class RateActivity : BaseActivity() {

    @Composable
    override fun Render() {
        RateTest()
    }

    override fun title(): String = "Rate示例"
}