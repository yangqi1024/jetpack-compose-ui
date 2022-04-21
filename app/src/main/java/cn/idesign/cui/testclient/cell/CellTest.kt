package cn.idesign.cui.testclient.cell

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.idesign.cui.cell.Cell
import cn.idesign.cui.testclient.R

@Composable
fun CellTest() {
    var checked by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    LazyColumn(Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item {
            Text(
                text = "基本用法",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Cell(text = "朋友圈")
        }

        item {
            Text(
                text = "显示分割线",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Column() {
                Cell(
                    text = "朋友圈", showDivider = true,
                    iconPainter = painterResource(id = R.drawable.acivity)
                )
                Cell(
                    text = "相册",
                    iconPainter = painterResource(id = R.drawable.acivity)
                )
            }
        }
        item {
            Text(
                text = "指定icon",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Cell(text = "朋友圈", iconPainter = painterResource(id = R.drawable.acivity))
        }
        item {
            Text(
                text = "显示摘要信息",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Cell(
                text = "朋友圈",
                secondaryText = "摘要信息"
            )
        }

        item {
            Text(
                text = "显示详细信息",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Cell(
                text = "朋友圈",
                rightText = "详细信息",
            )
        }

        item {
            Text(
                text = "自定义右图标",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Column {
                Cell(
                    text = "朋友圈",
                    rightComponent = {
                        Switch(checked = checked, onCheckedChange = { checked = it })
                    },
                    showDivider = true
                )
                Cell(
                    text = "朋友圈",
                    rightText = "详细信息",
                    rightComponent = {
                        Switch(checked = checked, onCheckedChange = { checked = it })
                    }
                )
            }
        }

        item {
            Text(
                text = "点击事件",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Cell(
                text = "朋友圈",
                rightText = "详细信息",
                rightComponent = {
                    Switch(checked = checked, onCheckedChange = { checked = it })
                },
                onClick = {
                    Toast.makeText(context, "点击了条目", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}