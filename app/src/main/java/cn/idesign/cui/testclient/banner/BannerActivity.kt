package cn.idesign.cui.testclient.banner

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class BannerActivity : BaseActivity() {

    @Composable
    override fun Render() {
        BannerTest()
    }

    override fun title(): String = "Banner示例"
}