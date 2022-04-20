package cn.idesign.cui.testclient.annotatedtext

import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import cn.idesign.cui.annotatedtext.AnnotatedAction
import cn.idesign.cui.annotatedtext.AnnotatedText

@Composable
fun AnnotatedTextTest() {
    val context = LocalContext.current
    LazyColumn(Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item {
            Text(
                text = "指定文字",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            AnnotatedText(
                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.surface).padding(10.dp),
                text = "我已阅读并同意《隐私政策》",
                annotatedActions = listOf(AnnotatedAction("隐私政策") { tag ->
                    Toast.makeText(context, "点击了：${tag}", Toast.LENGTH_SHORT).show()
                })
            )
        }
        item {
            Text(
                text = "手机号样式",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            AnnotatedText(
                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.surface).padding(10.dp),
                text = "您的订单已代收，如有疑问您可以联系配送员【张三，18710220022】确认，感谢您购物，欢迎再次光临。",
                annotatedActions = listOf(AnnotatedAction("1[3-9][0-9]{9}") { tag ->
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${tag}"))
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                })
            )
        }
        item {
            Text(
                text = "邮箱样式",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            AnnotatedText(
                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.surface).padding(10.dp),
                text = "邮件已发送至您的邮箱：549226148@qq.com。",
                annotatedActions = listOf(AnnotatedAction("\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*") { tag ->
                    Toast.makeText(context, "点击了：${tag}", Toast.LENGTH_SHORT).show()
                }),
                annotatedStyle = SpanStyle(
                    color = MaterialTheme.colors.secondary,
                    textDecoration = TextDecoration.Underline
                )
            )
        }
    }
}