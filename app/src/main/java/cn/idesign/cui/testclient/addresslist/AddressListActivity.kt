package cn.idesign.cui.testclient.addresslist

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class AddressListActivity : BaseActivity() {

    @Composable
    override fun Render() {
        AddressListTest()
    }

    override fun title(): String = "AddressList示例"
}