package cn.idesign.cui.testclient.searchbar

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SearchBarTest() {
    val context = LocalContext.current
    var searchValue by remember {
        mutableStateOf("")
    }
    var numberSearchValue by remember {
        mutableStateOf("")
    }
    Column(Modifier.padding(10.dp)) {
        Text(text = "基础用法")
        SearchBar(value = searchValue) { searchValue = it }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "显示按钮")
        SearchBar(value = searchValue, showAction = true) { searchValue = it }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "自定义按钮文字")
        SearchBar(value = searchValue, showAction = true, actionText = "搜一下") { searchValue = it }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "自定义搜索类型")
        SearchBar(
            value = numberSearchValue,
            showAction = true,
            placeholder = "请输入数字",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onActionClick = {
                Toast.makeText(context, "点击搜索", Toast.LENGTH_SHORT).show()
            }
        ) { numberSearchValue = it }
    }
}