package cn.idesign.cui.testclient.curtain

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import cn.idesign.cui.curtain.Curtain
import cn.idesign.cui.curtain.CurtainAlignment
import cn.idesign.cui.curtain.CurtainModel
import cn.idesign.cui.modal.rememberModalState
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode

@SuppressLint("UnrememberedMutableState", "RememberReturnType")
@Composable
fun CurtainTest() {
    val context = LocalContext.current
    val commonState = rememberModalState()
    val multiState = rememberModalState()
    val closeAlignmentState = rememberModalState()
    val customSizeState = rememberModalState()
    var closeCurtainAlignment: CurtainAlignment by remember {
        mutableStateOf(CurtainAlignment.TopStart)
    }

    LazyColumn(Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item {
            Text(
                text = "基础用法",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .background(color = MaterialTheme.colors.primary.copy(ContentAlpha.medium))
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onPrimary.copy(ContentAlpha.high)
            )
            Button(onClick = {
                commonState.show()
            }) {
                Text(text = "底部居中")
            }
        }
        item {
            Text(
                text = "多张图片",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .background(color = MaterialTheme.colors.primary.copy(ContentAlpha.medium))
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onPrimary.copy(ContentAlpha.high)
            )
            Button(onClick = {
                multiState.show()
            }) {
                Text(text = "多张图片")
            }
        }

        item {
            Text(
                text = "自定义大小",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .background(color = MaterialTheme.colors.primary.copy(ContentAlpha.medium))
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onPrimary.copy(ContentAlpha.high)
            )
            Button(onClick = {
                customSizeState.show()
            }) {
                Text(text = "自定义大小")
            }

        }

        item {
            Text(
                text = "关闭按钮位置",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .background(color = MaterialTheme.colors.primary.copy(ContentAlpha.medium))
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onPrimary.copy(ContentAlpha.high)
            )
            FlowRow(
                mainAxisAlignment = MainAxisAlignment.Center,
                mainAxisSize = SizeMode.Expand,
                crossAxisSpacing = 12.dp,
                mainAxisSpacing = 8.dp
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(0.3f),
                    onClick = {
                        closeCurtainAlignment = CurtainAlignment.TopStart
                        closeAlignmentState.show()
                    }) {
                    Text(text = "左上")
                }
                Button(
                    modifier = Modifier.fillMaxWidth(0.3f),
                    onClick = {
                        closeCurtainAlignment = CurtainAlignment.TopCenter
                        closeAlignmentState.show()
                    }) {
                    Text(text = "顶部")
                }
                Button(
                    modifier = Modifier.fillMaxWidth(0.3f),
                    onClick = {
                        closeCurtainAlignment = CurtainAlignment.TopEnd
                        closeAlignmentState.show()
                    }) {
                    Text(text = "右上")
                }
                Button(
                    modifier = Modifier.fillMaxWidth(0.3f),
                    onClick = {
                        closeCurtainAlignment = CurtainAlignment.BottomStart
                        closeAlignmentState.show()
                    }) {
                    Text(text = "左下")
                }
                Button(
                    modifier = Modifier.fillMaxWidth(0.3f),
                    onClick = {
                        closeCurtainAlignment = CurtainAlignment.BottomCenter
                        closeAlignmentState.show()
                    }) {
                    Text(text = "底部")
                }
                Button(
                    modifier = Modifier.fillMaxWidth(0.3f),
                    onClick = {
                        closeCurtainAlignment = CurtainAlignment.BottomEnd
                        closeAlignmentState.show()
                    }) {
                    Text(text = "右下")
                }
            }
        }
    }

    Curtain(
        state = commonState,
        url = "https://raw.githubusercontent.com/yipianfengye/android-adDialog/master/images/testImage2.png",
        onClick = {
            Toast.makeText(context, "点击了图片", Toast.LENGTH_SHORT).show()
        }
    )
    Curtain(
        state = customSizeState,
        url = "https://raw.githubusercontent.com/yipianfengye/android-adDialog/master/images/testImage2.png",
        size = DpSize(200.dp, 280.dp)
    )

    Curtain(
        state = multiState,
        data = listOf(
            CurtainModel("https://raw.githubusercontent.com/yipianfengye/android-adDialog/master/images/testImage1.png"),
            CurtainModel("https://raw.githubusercontent.com/yipianfengye/android-adDialog/master/images/testImage2.png")
        )
    )
    Curtain(
        state = closeAlignmentState,
        url = "https://raw.githubusercontent.com/yipianfengye/android-adDialog/master/images/testImage2.png",
        closeAlignment = closeCurtainAlignment
    )
}