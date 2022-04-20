package cn.idesign.cui.testclient.verifycode

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import cn.idesign.cui.verifycode.VerifyCode
import cn.idesign.cui.verifycode.VerifyType

@Composable
fun VerifyCodeTest() {
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
            VerifyCode(
            ) {
                Toast.makeText(context, "输入完成", Toast.LENGTH_SHORT).show()
            }
        }

        item {
            Text(
                text = "正方形用法",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            VerifyCode(
                type = VerifyType.Square
            ) {
                Toast.makeText(context, "输入完成", Toast.LENGTH_SHORT).show()
            }
        }
        item {
            Text(
                text = "自定义大小",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            VerifyCode(
                size = DpSize(45.dp, 45.dp)
            ) {
                Toast.makeText(context, "输入完成", Toast.LENGTH_SHORT).show()
            }
            Spacer(modifier = Modifier.height(10.dp))
            VerifyCode(
                size = DpSize(45.dp, 45.dp),
                type = VerifyType.Square,
            ) {
                Toast.makeText(context, "输入完成", Toast.LENGTH_SHORT).show()
            }
        }
        item {
            Text(
                text = "自定义数量",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            VerifyCode(
                count = 4,
            ) {
                Toast.makeText(context, "输入完成", Toast.LENGTH_SHORT).show()
            }
            Spacer(modifier = Modifier.height(10.dp))
            VerifyCode(
                count = 4,
                type = VerifyType.Square,
            ) {
                Toast.makeText(context, "输入完成", Toast.LENGTH_SHORT).show()
            }
        }
        item {
            Text(
                text = "自定义线宽",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            VerifyCode(
                lineHeight = 3.dp,
            ) {
                Toast.makeText(context, "输入完成", Toast.LENGTH_SHORT).show()
            }
            Spacer(modifier = Modifier.height(10.dp))
            VerifyCode(
                lineHeight = 3.dp,
                type = VerifyType.Square,
            ) {
                Toast.makeText(context, "输入完成", Toast.LENGTH_SHORT).show()
            }

        }

        item {
            Text(
                text = "自定义颜色",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            VerifyCode(
                activeLineColor = MaterialTheme.colors.primary,
                cursorLineColor = MaterialTheme.colors.secondary,
                textColor = MaterialTheme.colors.onSurface
            ) {
                Toast.makeText(context, "输入完成", Toast.LENGTH_SHORT).show()
            }
            Spacer(modifier = Modifier.height(10.dp))
            VerifyCode(
                type = VerifyType.Square,
                activeLineColor = MaterialTheme.colors.primary,
                cursorLineColor = MaterialTheme.colors.secondary,
                textColor = MaterialTheme.colors.error
            ) {
                Toast.makeText(context, "输入完成", Toast.LENGTH_SHORT).show()
            }

        }


    }

}