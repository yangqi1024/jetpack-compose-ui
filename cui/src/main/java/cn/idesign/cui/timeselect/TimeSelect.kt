package cn.idesign.cui.timeselect

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import cn.idesign.cui.bottomsheet.BottomSheet
import cn.idesign.cui.bottomsheet.BottomSheetState
import cn.idesign.cui.bottomsheet.rememberBottomSheetState
import kotlinx.coroutines.launch

/**
 * 类似于京东选择配送时间组件
 */
@Composable
fun TimeSelect(
    modifier: Modifier = Modifier,
    title: String? = null,
    state: BottomSheetState = rememberBottomSheetState(),
    selectKey: String,
    selectTime: List<String> = listOf(),
    panelActiveModifier: Modifier = Modifier,
    panelNormalModifier: Modifier = Modifier,
    panelActiveStyle: TextStyle = MaterialTheme.typography.body2.copy(MaterialTheme.colors.onSurface),
    panelNormalStyle: TextStyle = MaterialTheme.typography.body2.copy(
        MaterialTheme.colors.onSurface.copy(
            ContentAlpha.medium
        )
    ),
    timeActiveModifier: Modifier = Modifier,
    timeNormalModifier: Modifier = Modifier,
    timeActiveStyle: TextStyle = MaterialTheme.typography.body2.copy(MaterialTheme.colors.primary),
    timeNormalStyle: TextStyle = MaterialTheme.typography.body2,

    onKeyChange: ((key: String) -> Unit)? = null,
    onTimeChange: ((key: List<String>) -> Unit)? = null,

    content: TimeSelectScope.() -> Unit,
) {
    val timeSelectScopeImpl = TimeSelectScopeImpl().apply(content)
    val keyChangeState = rememberUpdatedState(onKeyChange)
    val timeChangeState = rememberUpdatedState(onTimeChange)
    val scope = rememberCoroutineScope()
    BottomSheet(
        state = state,
        content = {
            Column(
                Modifier
                    .fillMaxHeight(0.5f)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .background(MaterialTheme.colors.surface)
                    .then(modifier)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = title.orEmpty())
                    Icon(
                        Icons.Default.Close, contentDescription = null,
                        Modifier.clickable {
                            scope.launch {
                                state.hide()
                            }
                        }
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Column(
                        Modifier
                            .width(140.dp)
                            .fillMaxHeight()
                            .background(MaterialTheme.colors.background),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        repeat(timeSelectScopeImpl.intervals.size) { index ->
                            val panel = timeSelectScopeImpl.intervals[index]
                            var panelModifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    keyChangeState.value?.invoke(panel.key)
                                }

                            var style = panelNormalStyle
                            if (panel.key == selectKey) {
                                panelModifier =
                                    panelModifier
                                        .background(MaterialTheme.colors.surface)
                                        .padding(15.dp)
                                        .then(panelActiveModifier)
                                style = panelActiveStyle
                            } else {
                                panelModifier =
                                    panelModifier
                                        .padding(15.dp)
                                        .then(panelNormalModifier)
                            }
                            Text(
                                text = panel.title,
                                modifier = panelModifier,
                                style = style
                            )
                        }
                    }
                    Column(
                        Modifier
                            .weight(1f)
                            .verticalScroll(state = rememberScrollState())
                            .background(MaterialTheme.colors.surface)
                            .padding(10.dp, 0.dp, 10.dp, 50.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        val selectItem =
                            timeSelectScopeImpl.intervals.find { it.key == selectKey }
                        val data = selectItem?.data
                        data?.size?.let {
                            repeat(it) { index ->
                                val time = data[index]
                                val contains = selectTime.contains(time)
                                var timeModifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        val _selectTime = selectTime.toMutableList()
                                        if (contains) {
                                            _selectTime.remove(time)
                                        } else {
                                            _selectTime.add(time)
                                        }
                                        timeChangeState.value?.invoke(_selectTime)
                                    }
                                var style = timeNormalStyle
                                if (contains) {
                                    timeModifier = timeModifier
                                        .background(
                                            MaterialTheme.colors.primary.copy(0.12f),
                                        )
                                        .border(
                                            1.dp,
                                            MaterialTheme.colors.primary,
                                            RoundedCornerShape(4.dp)
                                        )
                                        .padding(15.dp)
                                        .then(timeActiveModifier)

                                    style = timeActiveStyle
                                } else {
                                    timeModifier = timeModifier
                                        .background(
                                            MaterialTheme.colors.background
                                        )
                                        .border(
                                            1.dp,
                                            MaterialTheme.colors.onSurface.copy(0.12f),
                                            RoundedCornerShape(4.dp)
                                        )
                                        .padding(15.dp)
                                        .then(timeNormalModifier)
                                }
                                Text(
                                    text = data[index],
                                    modifier = timeModifier,
                                    style = style
                                )
                            }
                        }
                    }
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(text = "确定")
                }
            }
        }
    )
}