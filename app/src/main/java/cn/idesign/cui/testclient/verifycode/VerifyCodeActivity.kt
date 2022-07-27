package cn.idesign.cui.testclient.verifycode

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class VerifyCodeActivity : BaseActivity() {

    @Composable
    override fun Render() {
        VerifyCodeTest()
    }

    override fun title(): String = "VerifyCode示例"
}