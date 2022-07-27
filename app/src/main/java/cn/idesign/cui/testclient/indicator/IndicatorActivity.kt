package cn.idesign.cui.testclient.indicator

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class IndicatorActivity : BaseActivity() {

    @Composable
    override fun Render() {
        IndicatorTest()
    }

    override fun title(): String = "Indicator示例"
}