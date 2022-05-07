package cn.idesign.cui.testclient.rate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import cn.idesign.cui.rate.Rate

@Composable
fun RateTest() {
    var commonValue by remember {
        mutableStateOf(0.0f)
    }
    var halfValue by remember {
        mutableStateOf(2.5f)
    }
    var customIconValue by remember {
        mutableStateOf(3f)
    }
    var customStyleValue by remember {
        mutableStateOf(3f)
    }
    var readOnlyValue by remember {
        mutableStateOf(3f)
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
            Rate(value = commonValue, onChange = { commonValue = it })
        }

        item {
            Text(
                text = "半星用法",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Rate(value = halfValue, onChange = { halfValue = it }, allowHalf = true)
        }


        item {
            Text(
                text = "只读状态",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Rate(
                value = readOnlyValue,
                onChange = { readOnlyValue = it },
                readOnly = true
            )
        }

        item {
            Text(
                text = "自定义图标",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Rate(
                value = customIconValue,
                onChange = { customIconValue = it },
                activeIcon = Icons.Filled.Favorite,
                normalIcon = Icons.Filled.FavoriteBorder
            )
        }
        item {
            Text(
                text = "自定义样式",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Rate(
                value = customStyleValue,
                onChange = { customStyleValue = it },
                activeColor = Color(0xFFEE0A24),
                size = DpSize(30.dp, 30.dp)
            )
        }
    }
}