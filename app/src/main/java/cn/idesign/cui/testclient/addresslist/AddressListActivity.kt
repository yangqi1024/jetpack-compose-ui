package cn.idesign.cui.testclient.addresslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import cn.idesign.cui.testclient.ui.theme.CUITestTheme

class AddressListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CUITestTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "AddressList示例",
                                    color = MaterialTheme.colors.onPrimary
                                )
                            },
                            backgroundColor = MaterialTheme.colors.primary,
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) {
                    AddressListTest()
                }
            }
        }
    }
}