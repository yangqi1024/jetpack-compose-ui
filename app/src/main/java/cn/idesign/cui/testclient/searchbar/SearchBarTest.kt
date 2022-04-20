package cn.idesign.cui.testclient.searchbar

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SearchBarTest() {
    val context = LocalContext.current
    var commonSearchValue by remember {
        mutableStateOf("")
    }
    var showActionValue by remember {
        mutableStateOf("")
    }
    var customActionValue by remember {
        mutableStateOf("")
    }

    var numberSearchValue by remember {
        mutableStateOf("")
    }
    LazyColumn(Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item {
            Text(
                text = "基础用法",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            SearchBar(value = commonSearchValue) { commonSearchValue = it }
        }
        item {
            Text(
                text = "显示按钮",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            SearchBar(value = showActionValue, showAction = true) { showActionValue = it }
        }

        item {
            Text(
                text = "自定义按钮文字",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            SearchBar(value = customActionValue, actionText = "搜一下") { customActionValue = it }
        }
        item {
            Text(
                text = "自定义搜索类型",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            SearchBar(
                value = numberSearchValue,
                placeholder = "请输入数字",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onActionClick = {
                    Toast.makeText(context, "点击搜索", Toast.LENGTH_SHORT).show()
                }
            ) { numberSearchValue = it }
        }
    }
}