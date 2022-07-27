package cn.idesign.cui.testclient.stepper

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class StepperActivity : BaseActivity() {

    @Composable
    override fun Render() {
        StepperTest()
    }

    override fun title(): String = "Stepper示例"
}