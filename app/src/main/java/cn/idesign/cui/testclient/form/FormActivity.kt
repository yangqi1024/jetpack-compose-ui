package cn.idesign.cui.testclient.form

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class FormActivity : BaseActivity() {

    @Composable
    override fun Render() {
        FormTest()
    }

    override fun title(): String = "Form示例"
}