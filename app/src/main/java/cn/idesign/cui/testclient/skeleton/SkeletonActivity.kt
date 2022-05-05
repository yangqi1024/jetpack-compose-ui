package cn.idesign.cui.testclient.skeleton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import cn.idesign.cui.testclient.ui.theme.CUITestTheme

class SkeletonActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CUITestTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Skeleton示例",
                                    color = MaterialTheme.colors.onPrimary
                                )
                            },
                            backgroundColor = MaterialTheme.colors.primary,
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) {
                    SkeletonTest()
                }
            }
        }
    }
}