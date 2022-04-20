package cn.idesign.cui.testclient.statefullayout

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cn.idesign.cui.statefullayout.Event
import cn.idesign.cui.statefullayout.StatefulLayout
import cn.idesign.cui.statefullayout.StatefulStatus
import cn.idesign.cui.statefullayout.rememberStatefulState
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@Composable
fun StatefulLayoutTest() {
    val context = LocalContext.current
    val state = rememberStatefulState()
    Column {
        FlowRow(mainAxisSpacing = 10.dp, mainAxisAlignment = MainAxisAlignment.SpaceAround) {
            Button(onClick = { state.currentState = StatefulStatus.Loading }) {
                Text(text = "Loading")
            }
            Button(onClick = { state.currentState = StatefulStatus.Error }) {
                Text(text = "Error")
            }
            Button(onClick = { state.currentState = StatefulStatus.NetError }) {
                Text(text = "NetError")
            }
            Button(onClick = { state.currentState = StatefulStatus.LocationOff }) {
                Text(text = "LocationOff")
            }

            Button(onClick = { state.currentState = StatefulStatus.Content }) {
                Text(text = "Content")
            }
        }
        Divider()
        StatefulLayout(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            event = Event(
                onErrorRetry = {
                    Toast.makeText(context, "点击了错误重试", Toast.LENGTH_SHORT).show()
                },
                onNetRetry = {
                    Toast.makeText(context, "点击了网络错误重试", Toast.LENGTH_SHORT).show()
                },
                onLocationRetry = {
                    Toast.makeText(context, "点击了定位错误重试", Toast.LENGTH_SHORT).show()
                },
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "内容")
            }
        }
    }
}