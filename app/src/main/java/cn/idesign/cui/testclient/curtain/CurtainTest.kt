package cn.idesign.cui.testclient.curtain

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import cn.idesign.cui.cell.Cell
import cn.idesign.cui.curtain.Curtain
import cn.idesign.cui.curtain.CurtainAlignment
import cn.idesign.cui.curtain.CurtainModel
import cn.idesign.cui.modal.rememberModalState
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import kotlinx.coroutines.launch

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
            Cell(
                text = "基本用法", showDivider = true, onClick = {
                    commonState.show()
                }
            )
        }
        item {
            Cell(
                text = "多张图片", showDivider = true, onClick = {
                    multiState.show()
                }
            )
        }

        item {
            Cell(
                text = "自定义大小", showDivider = true, onClick = {
                    customSizeState.show()
                }
            )
        }

        item {
            Text(
                text = "关闭按钮位置",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
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
        url = "https://img.zcool.cn/community/01c4605ddd117aa801215972c06064.jpg@2o.jpg",
        onClick = {
            Toast.makeText(context, "点击了图片", Toast.LENGTH_SHORT).show()
        }
    )
    Curtain(
        state = customSizeState,
        url = "https://img.zcool.cn/community/01c4605ddd117aa801215972c06064.jpg@2o.jpg",
        size = DpSize(200.dp, 280.dp)
    )

    Curtain(
        state = multiState,
        data = listOf(
            CurtainModel("https://tse1-mm.cn.bing.net/th/id/R-C.1c34c450cf77bff7f075d3155c1791b4?rik=5lPXTF0HCSr%2fYg&riu=http%3a%2f%2fimg.aiimg.com%2fuploads%2fuserup%2f0912%2f100T13414V.jpg&ehk=ZK9E8KaxPFjr6aEOMxmSQzOiHO29x4XMdEaAuzueQ5w%3d&risl=&pid=ImgRaw&r=0"),
            CurtainModel("https://img.zcool.cn/community/01c4605ddd117aa801215972c06064.jpg@2o.jpg")
        )
    )
    Curtain(
        state = closeAlignmentState,
        url = "https://img.zcool.cn/community/01c4605ddd117aa801215972c06064.jpg@2o.jpg",
        closeAlignment = closeCurtainAlignment
    )
}