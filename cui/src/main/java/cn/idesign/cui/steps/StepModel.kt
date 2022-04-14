package cn.idesign.cui.steps

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class StepModel(
    val text: String? = null,
    val secondaryText: String? = null,
    val textColor: Color = Color.Unspecified,
    val secondaryTextColor: Color = Color.Unspecified,
    val status: StepStatus? = null,
    val icon: (@Composable () -> Unit)? = null,
    val showIndex: Boolean = true,
    val iconBoxSize: Dp = 20.dp,
    val iconSize: Dp = 20.dp,
)


sealed class StepStatus {
    object Finish : StepStatus()
    object Error : StepStatus()
    object Wait : StepStatus()
    object Process : StepStatus()
}