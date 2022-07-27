package cn.idesign.cui.testclient.collapse

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class CollapseActivity : BaseActivity() {

    @Composable
    override fun Render() {
        CollapseTest()
    }

    override fun title(): String = "Collapse示例"
}