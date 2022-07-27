package cn.idesign.cui.testclient.annotatedtext

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class AnnotatedTextActivity : BaseActivity() {

    @Composable
    override fun Render() {
        AnnotatedTextTest()
    }

    override fun title(): String = "AnnotatedText示例"
}