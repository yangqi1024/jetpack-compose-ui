package cn.idesign.cui.testclient.preview

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.idesign.cui.modal.rememberModalState
import cn.idesign.cui.preview.Preview
import cn.idesign.cui.preview.PreviewActivity
import cn.idesign.cui.preview.PreviewItem

@Composable
fun PreviewTest() {
    val list = arrayListOf(
        PreviewItem(url = "https://image.uisdc.com/wp-content/uploads/2018/12/uisdc-jl-20181224-36.jpg"),
        PreviewItem(url = "https://image.uisdc.com/wp-content/uploads/2018/12/uisdc-jl-20181224-55.jpg"),
        PreviewItem(url = "https://image.uisdc.com/wp-content/uploads/2018/12/uisdc-jl-20181224-34.jpg"),
        PreviewItem(url = "https://image.uisdc.com/wp-content/uploads/2018/12/uisdc-jl-20181224-35.jpg"),
    )
    val commonState = rememberModalState()
    val customInitState = rememberModalState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
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
            Button(onClick = {
                commonState.show()
//                startToPreviewActivity(context = context, list)
            }) {
                Text(text = "预览图片")
            }
        }
        item {
            Text(
                text = "指定初始位置",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Button(onClick = {
                customInitState.show()
//                startToPreviewActivity(context = context, list, 2)
            }) {
                Text(text = "指定初始位置")
            }
        }
        item {
            Text(
                text = "打开新的Activity",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Button(onClick = {
                startToPreviewActivity(context = context, list)
            }) {
                Text(text = "打开新的Activity")
            }
        }
    }

    Preview(data = list, state = commonState, modifier = Modifier.height(LocalDensity.current.run {
        Resources.getSystem().displayMetrics.heightPixels.toDp()
    }))

    Preview(
        data = list, state = customInitState,
        initialPage = 2, modifier = Modifier.height(LocalDensity.current.run {
            Resources.getSystem().displayMetrics.heightPixels.toDp()
        })
    )
}

fun startToPreviewActivity(context: Context, data: ArrayList<PreviewItem>, initialPage: Int = 0) {
    val intent = Intent(context, PreviewActivity::class.java)
    val bundle = Bundle()
    bundle.putParcelableArrayList(PreviewActivity.KEY_DATA, data)
    bundle.putInt(PreviewActivity.KEY_INITIALPAGE, initialPage)
    intent.putExtras(bundle)
    context.startActivity(intent)
}