package cn.idesign.cui.testclient.cascade

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class CascadeActivity : BaseActivity() {

    @Composable
    override fun Render() {
        CascadeTest()
    }

    override fun title(): String = "Cascade示例"
}