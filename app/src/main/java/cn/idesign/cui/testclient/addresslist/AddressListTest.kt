package cn.idesign.cui.testclient.addresslist

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import cn.idesign.cui.addresslist.AddressList
import cn.idesign.cui.addresslist.AddressModel

@Composable
fun AddressListTest() {
    val data = listOf(
        AddressModel(
            area = "陕西省西安市未央区",
            address = "凤城五路",
            receiver = "张三",
            phoneNumber = "132****2313",
            tag = "公司"
        ),
        AddressModel(
            area = "陕西省西安市雁塔区",
            address = "劳动路",
            receiver = "王五",
            phoneNumber = "182****2413",
        ),
        AddressModel(
            area = "陕西省西安市长安区",
            address = "锦业一路",
            receiver = "李四",
            phoneNumber = "142****4339",
            defaultAddress = true
        ),
        AddressModel(
            area = "陕西省西安市碑林区",
            address = "锦业一路",
            receiver = "小明",
            phoneNumber = "142****4339",
            tag = "家"
        )
    )
    val context = LocalContext.current
    AddressList(data = data,
        onClick = { model ->
            Toast.makeText(context, "点击了:${model.receiver}", Toast.LENGTH_SHORT).show()
        },
        onDelete = { model ->
            Toast.makeText(context, "点击删除:${model.receiver}", Toast.LENGTH_SHORT).show()
        },
        onDefault = { model ->
            Toast.makeText(context, "点击设置默认:${model.receiver}", Toast.LENGTH_SHORT).show()
        }
    )
}