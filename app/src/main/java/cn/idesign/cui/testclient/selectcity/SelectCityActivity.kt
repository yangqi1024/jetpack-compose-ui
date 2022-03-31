package cn.idesign.cui.testclient.selectcity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cn.idesign.cui.selectcity.SelectCity
import cn.idesign.cui.testclient.ui.theme.CUITestTheme

class SelectCityActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CUITestTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "SelectCity示例",
                                    color = MaterialTheme.colors.onPrimary
                                )
                            },
                            backgroundColor = MaterialTheme.colors.primary,
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) {
                    SelectCityTest()
                }
            }
        }
    }
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