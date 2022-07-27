package cn.idesign.cui.testclient.signaturepad

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class SignaturePadActivity : BaseActivity() {

    @Composable
    override fun Render() {
        SignaturePadTest()
    }

    override fun title(): String = "SignaturePad示例"
}