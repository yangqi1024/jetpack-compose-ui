package cn.idesign.cui.testclient.image

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class NetworkImageActivity : BaseActivity() {

    @Composable
    override fun Render() {
        NetworkImageTest()
    }

    override fun title(): String = "NetworkImage示例"
}