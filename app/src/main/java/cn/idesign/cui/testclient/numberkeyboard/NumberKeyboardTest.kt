package cn.idesign.cui.testclient.numberkeyboard

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.idesign.cui.bottomsheet.rememberBottomSheetState
import cn.idesign.cui.cell.Cell
import cn.idesign.cui.numberkeyboard.NumberKeyBoard
import kotlinx.coroutines.launch

@Composable
fun NumberKeyboardTest() {
    val commonState = rememberBottomSheetState()
    val randomState = rememberBottomSheetState()
    val customState = rememberBottomSheetState()
    val disableState = rememberBottomSheetState()
    val scope = rememberCoroutineScope()
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
            Cell(text = "默认键盘", onClick = {
                scope.launch {
                    commonState.show()
                }
            })
        }
        item {
            Text(
                text = "随机数键盘",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Cell(text = "随机数键盘", onClick = {
                scope.launch {
                    randomState.show()
                }
            })
        }

        item {
            Text(
                text = "自定义用法",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Cell(text = "自定义用法", onClick = {
                scope.launch {
                    customState.show()
                }
            })
        }
        item {
            Text(
                text = "禁用确认按钮",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Cell(text = "禁用确认按钮", onClick = {
                scope.launch {
                    disableState.show()
                }
            })
        }
    }

    NumberKeyBoard(
        state = commonState,
        onInput = {
            Toast.makeText(context, "点击了：${it}", Toast.LENGTH_SHORT).show()

        },
        onDelete = {
            Toast.makeText(context, "点击了：删除", Toast.LENGTH_SHORT).show()
        },
        onConfirm = {
            Toast.makeText(context, "点击了：确定", Toast.LENGTH_SHORT).show()
        }
    )
    NumberKeyBoard(
        state = customState, confirmText = "支付", confirmModifier = Modifier.background(
            Color(0xff66bb6a)
        )
    )
    NumberKeyBoard(state = randomState, random = true)
    NumberKeyBoard(state = disableState, confirmDisable = true)
}