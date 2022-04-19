package cn.idesign.cui.testclient.privacypolicy

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.dp
import cn.idesign.cui.annotatedtext.AnnotatedAction
import cn.idesign.cui.modal.rememberModalState
import cn.idesign.cui.privacypolicy.PrivacyPolicy

@Composable
fun PrivacyPolicyTest() {
    val commonState = rememberModalState()
    val customColorState = rememberModalState()
    val context = LocalContext.current
    LazyColumn(Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item {
            Text(
                text = "基本用法",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .background(color = MaterialTheme.colors.primary.copy(ContentAlpha.medium))
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onPrimary.copy(ContentAlpha.high)
            )

            Button(onClick = { commonState.show() }) {
                Text(text = "打开用户协议")
            }

            PrivacyPolicy(
                state = commonState,
                text = "用户协议及隐私政策概要",
                secondaryText = "欢迎使用虎牙直播！我们将通过《服务协议》和《隐私政策》帮助您了解我们所提供的服务以及我们收集和处理您个人信息的情况，并告知您所享有的相关权利。\n" +
                        "为向您提供更加安全、便捷、个性化的服务，虎牙直播会在您使用相关功能时申请有关设备权限，您可通过\"系统权限设置\"查阅并选择是否授权我们收集这些信息。 \n" +
                        "您也可以通过\"设置\"-\"隐私设置\"来管理个性化推荐、个性化广告内容。 \n" +
                        "请您无比在使用本软件前仔细阅读上述法律文件，若您同意，请点击\"同意\"后开始使用我们的服务。",
                annotatedAction = listOf(
                    AnnotatedAction("服务协议") { tag ->
                        Toast.makeText(context, "点击了:${tag}", Toast.LENGTH_SHORT).show()
                    },
                    AnnotatedAction("隐私政策") { tag ->
                        Toast.makeText(context, "点击了:${tag}", Toast.LENGTH_SHORT).show()
                    },
                    AnnotatedAction("设备权限") { tag ->
                        Toast.makeText(context, "点击了:${tag}", Toast.LENGTH_SHORT).show()
                    }
                ),
                onCancelClick = {
                    Toast.makeText(context, "点击了不同意", Toast.LENGTH_SHORT).show()
                    commonState.hide()
                },
                onOkClick = {
                    Toast.makeText(context, "点击了同意", Toast.LENGTH_SHORT).show()
                    commonState.hide()
                },
            )
        }

        item {
            Text(
                text = "自定义颜色",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .background(color = MaterialTheme.colors.primary.copy(ContentAlpha.medium))
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onPrimary.copy(ContentAlpha.high)
            )

            Button(onClick = { customColorState.show() }) {
                Text(text = "自定义颜色")
            }

            PrivacyPolicy(
                state = customColorState,
                text = "用户协议及隐私政策概要",
                secondaryText = "欢迎使用虎牙直播！我们将通过《服务协议》和《隐私政策》帮助您了解我们所提供的服务以及我们收集和处理您个人信息的情况，并告知您所享有的相关权利。\n" +
                        "为向您提供更加安全、便捷、个性化的服务，虎牙直播会在您使用相关功能时申请有关设备权限，您可通过\"系统权限设置\"查阅并选择是否授权我们收集这些信息。 \n" +
                        "您也可以通过\"设置\"-\"隐私设置\"来管理个性化推荐、个性化广告内容。 \n" +
                        "请您无比在使用本软件前仔细阅读上述法律文件，若您同意，请点击\"同意\"后开始使用我们的服务。",
                annotatedAction = listOf(
                    AnnotatedAction("服务协议") { tag ->
                        Toast.makeText(context, "点击了:${tag}", Toast.LENGTH_SHORT).show()
                    },
                    AnnotatedAction("隐私政策") { tag ->
                        Toast.makeText(context, "点击了:${tag}", Toast.LENGTH_SHORT).show()
                    },
                    AnnotatedAction("设备权限") { tag ->
                        Toast.makeText(context, "点击了:${tag}", Toast.LENGTH_SHORT).show()
                    }
                ),
                onCancelClick = {
                    Toast.makeText(context, "点击了不同意", Toast.LENGTH_SHORT).show()
                    customColorState.hide()
                },
                onOkClick = {
                    Toast.makeText(context, "点击了同意", Toast.LENGTH_SHORT).show()
                    customColorState.hide()
                },
                annotatedStyle = SpanStyle(color = MaterialTheme.colors.secondary),
                okButtonColors = ButtonDefaults.buttonColors(backgroundColor = Color(0xfff1ad3b))

            )
        }
    }
}