package cn.idesign.cui.testclient.selectcity

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cn.idesign.cui.testclient.BaseActivity
import cn.idesign.cui.testclient.ui.theme.CUITestTheme

class SelectCityActivity : BaseActivity() {

    @Composable
    override fun Render() {
        SelectCityTest()
    }

    override fun title(): String = "SelectCity示例"
}

@Preview("previewSelectCity")
@Composable
fun previewSelectCity() {
    CUITestTheme {
        Surface() {
            SelectCityTest()
        }
    }
}