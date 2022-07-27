package cn.idesign.cui.testclient.privacypolicy

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class PrivacyPolicyActivity : BaseActivity() {

    @Composable
    override fun Render() {
        PrivacyPolicyTest()
    }

    override fun title(): String = "PrivacyPolicy示例"
}