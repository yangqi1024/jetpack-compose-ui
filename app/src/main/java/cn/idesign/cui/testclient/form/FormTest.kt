package cn.idesign.cui.testclient.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.idesign.cui.form.Form
import cn.idesign.cui.form.FormField
import cn.idesign.cui.form.FormInput
import cn.idesign.cui.form.FormItemType
import cn.idesign.cui.form.composable
import cn.idesign.cui.form.rememberFormState

@Composable
fun FormTest() {
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
            val state = rememberFormState()
            Form(
                state = state,
            ){
                composable(
                    label = "姓名",
                    name = "username",
                ) {
                    FormInput(state = it)
                }
                composable(
                    label = "手机号",
                    name = "mobile",
                ) {
                    FormInput(state = it)
                }

                composable(type = FormItemType.Submit) {
                    Button(onClick = {state.submit() }) {
                        Text(text = "提交")
                    }
                }
            }

        }

    }
}