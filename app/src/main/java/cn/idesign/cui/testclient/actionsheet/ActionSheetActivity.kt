package cn.idesign.cui.testclient.actionsheet

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cn.idesign.cui.testclient.BaseActivity
import cn.idesign.cui.testclient.ui.theme.CUITestTheme

class ActionSheetActivity : BaseActivity() {

    @Composable
    override fun Render() {
        ActionSheetTest()
    }

    override fun title(): String = "ActionSheet示例"
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CUITestTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "ActionSheet示例",
                            color = MaterialTheme.colors.onPrimary
                        )
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                )
            },
            modifier = Modifier.fillMaxSize()
        ) {
            ActionSheetTest()
        }
    }
}