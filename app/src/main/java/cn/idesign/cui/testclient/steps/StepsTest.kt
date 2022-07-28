package cn.idesign.cui.testclient.steps

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.idesign.cui.annotatedtext.AnnotatedAction
import cn.idesign.cui.common.Direction
import cn.idesign.cui.steps.StepModel
import cn.idesign.cui.steps.StepStatus
import cn.idesign.cui.steps.Steps
import cn.idesign.cui.steps.rememberStepState

@Composable
fun StepsTest() {
    val context = LocalContext.current
    val horizontalData = listOf(
        StepModel("步骤1"),
        StepModel("步骤2"),
        StepModel("步骤3"),
    )

    val verticalData = listOf(
        StepModel("步骤1", "步骤1的描述"),
        StepModel("步骤2", "步骤2的描述"),
        StepModel("步骤3", "步骤3的描述"),
        StepModel("步骤4", "步骤4的描述")
    )

    val logisticsData = listOf(
        StepModel(
            "已签收",
            "您的订单已代收，如有疑问您可以联系配送员【张三，18710220022】确认，感谢您购物，欢迎再次光临。\n2022-04-13 12:16:00",
            status = StepStatus.Finish
        ),
        StepModel(
            "派送中", "您的订单正在派送途中（快递员：张三，电话：18710220022），请您耐心等待。\n" +
                    "2022-04-12 12:16:00",
            status = StepStatus.Wait,
            showIndex = false,
            iconSize = 10.dp
        ),
        StepModel(
            "运输中", "您的订单已到达【西安徐家湾营业部】\n" +
                    "2022-04-12 10:16:00", status = StepStatus.Wait,
            showIndex = false,
            iconSize = 10.dp
        ),
        StepModel(
            "运输中", "您的订单由【西安灞桥分拣中心】准备发往【西安徐家湾营业部】\n" +
                    "2022-04-12 08:16:00", status = StepStatus.Wait,
            showIndex = false,
            iconSize = 10.dp
        ),
        StepModel(
            "仓库处理中", "打包完成\n" +
                    "2022-04-11 06:16:00", status = StepStatus.Wait,
            showIndex = false,
            iconSize = 10.dp
        ),
        StepModel(
            secondaryText = "拣货完成\n" +
                    "2022-04-11 05:16:00", status = StepStatus.Wait,
            showIndex = false,
            iconSize = 10.dp
        ),
        StepModel(
            secondaryText = "您的订单打印完成\n" +
                    "2022-04-11 05:10:00", status = StepStatus.Wait,
            showIndex = false,
            iconSize = 10.dp
        ),
        StepModel(
            "已下单", "您提交订单 \n" +
                    "2022-04-11 04:16:00", status = StepStatus.Wait,
            showIndex = false,
            iconSize = 10.dp
        )
    )

    val stepState = rememberStepState()
    val verticaStepState = rememberStepState()
    LazyColumn(Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item {
            Text(
                text = "横向步骤条",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Steps(
                state = stepState,
                data = horizontalData,
                direction = Direction.Horizontal,
                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.surface)
            )

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = { stepState.back() }, enabled = stepState.currentValue > 0) {
                    Text(text = "Back")
                }
                Button(
                    onClick = { stepState.next() },
                    enabled = stepState.currentValue < stepState.size
                ) {
                    Text(text = "Next")
                }
            }
        }
        item {
            Text(
                text = "竖直步骤条",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Steps(
                modifier = Modifier.background(MaterialTheme.colors.surface),
                state = verticaStepState,
                data = verticalData
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { verticaStepState.back() },
                    enabled = verticaStepState.currentValue > 0
                ) {
                    Text(text = "Back")
                }
                Button(
                    onClick = { verticaStepState.next() },
                    enabled = verticaStepState.currentValue < verticaStepState.size
                ) {
                    Text(text = "Next")
                }
            }
        }
        item {
            Text(
                text = "物流信息",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Steps(
                modifier = Modifier.background(MaterialTheme.colors.surface),
                data = logisticsData,
                annotatedAction = listOf(AnnotatedAction("1[3-9][0-9]{9}") { tag ->
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${tag}"))
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                })
            )
        }
    }
}