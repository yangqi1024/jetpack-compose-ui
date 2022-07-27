package cn.idesign.cui.testclient.noticebar

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class NoticeBarActivity : BaseActivity() {

    @Composable
    override fun Render() {
        NoticeBarTest()
    }

    override fun title(): String = "NoticeBar示例"
}