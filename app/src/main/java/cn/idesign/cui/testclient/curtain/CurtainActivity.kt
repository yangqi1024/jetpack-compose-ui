package cn.idesign.cui.testclient.curtain

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class CurtainActivity : BaseActivity() {

    @Composable
    override fun Render() {
        CurtainTest()
    }

    override fun title(): String = "Curtain示例"
}