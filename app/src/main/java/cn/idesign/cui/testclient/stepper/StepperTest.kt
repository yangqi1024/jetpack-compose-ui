package cn.idesign.cui.testclient.stepper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.idesign.cui.stepper.Stepper
import cn.idesign.cui.stepper.rememberStepperState

@Composable
fun StepperTest() {
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Stepper()
            }

        }

        item {
            Text(
                text = "步长设置",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Stepper(step = 10)
            }
        }
        item {
            Text(
                text = "范围设置",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Stepper(max = 5, min = -5)
            }
        }

        item {
            Text(
                text = "初始化值设置",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Stepper(state = rememberStepperState(6))
            }
        }

        item {
            Text(
                text = "输入框只读",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Stepper(inputReadOnly = true)
            }
        }


        item {
            Text(
                text = "禁用状态",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Stepper(disable = true)
            }
        }
    }
}