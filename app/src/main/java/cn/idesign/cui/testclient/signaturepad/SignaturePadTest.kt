package cn.idesign.cui.testclient.signaturepad

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.idesign.cui.signaturepad.SignaturePad
import cn.idesign.cui.signaturepad.rememberSignaturePadState
import kotlinx.coroutines.launch

@Composable
fun SignaturePadTest() {
    val commonState = rememberSignaturePadState()
    val customState = rememberSignaturePadState()
    val scope = rememberCoroutineScope()
    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }
    var customBitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }
    LazyColumn(
        Modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {


            Text(
                text = "基础用法",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            SignaturePad(state = commonState)
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                Button(onClick = { commonState.clear() }) {
                    Text(text = "清空")
                }
                Button(onClick = {
                    scope.launch {
                        bitmap = commonState.save()
                    }
                }) {
                    Text(text = "保存")
                }
            }
            bitmap?.let {
                Image(bitmap = bitmap!!.asImageBitmap(), contentDescription = null)
            }
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
            SignaturePad(state = customState, color = Color.Blue, stokeWidth = 8.dp)
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                Button(onClick = { customState.clear() }) {
                    Text(text = "清空")
                }
                Button(onClick = {
                    scope.launch {
                        customBitmap = customState.save()
                    }
                }) {
                    Text(text = "保存")
                }
            }
            customBitmap?.let {
                Image(bitmap = customBitmap!!.asImageBitmap(), contentDescription = null)
            }
        }
    }


}

