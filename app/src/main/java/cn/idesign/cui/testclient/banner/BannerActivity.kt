package cn.idesign.cui.testclient.banner

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import cn.idesign.cui.testclient.ui.theme.CUITheme

class BannerActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CUITheme {
                Surface {
                    BannerTest()
                }
            }
        }
    }
}