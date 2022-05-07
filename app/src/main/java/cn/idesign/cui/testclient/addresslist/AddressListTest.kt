package cn.idesign.cui.testclient.addresslist

import androidx.compose.runtime.Composable
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
        )
    )
    AddressList(data = data)
}