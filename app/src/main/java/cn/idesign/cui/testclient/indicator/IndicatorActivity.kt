package cn.idesign.cui.testclient.indicator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import cn.idesign.cui.testclient.ui.theme.CUITheme

class IndicatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CUITheme {
                Surface {
                    IndicatorTest()
                }
            }
        }
    }
}