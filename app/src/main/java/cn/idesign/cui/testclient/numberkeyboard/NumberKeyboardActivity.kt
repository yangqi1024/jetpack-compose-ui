package cn.idesign.cui.testclient.numberkeyboard

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class NumberKeyboardActivity : BaseActivity() {

    @Composable
    override fun Render() {
        NumberKeyboardTest()
    }

    override fun title(): String = "NumberKeyboard示例"
}