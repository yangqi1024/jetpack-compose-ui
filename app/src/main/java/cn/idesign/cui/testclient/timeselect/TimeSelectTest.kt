package cn.idesign.cui.testclient.timeselect

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cn.idesign.cui.bottomsheet.rememberBottomSheetState
import cn.idesign.cui.cell.Cell
import cn.idesign.cui.timeselect.TimeSelect
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("MutableCollectionMutableState")
@Composable
fun TimeSelectTest() {
    val commonState = rememberBottomSheetState()
    val customState = rememberBottomSheetState()
    val customSelectState = rememberBottomSheetState()
    val scope = rememberCoroutineScope()
    var selectKey by remember {
        mutableStateOf("2月23日")
    }
    var selectTime by remember {
        mutableStateOf(Collections.EMPTY_LIST)
    }

    var customSelectKey by remember {
        mutableStateOf("2月24日")
    }
    var customSelectTime by remember {
        mutableStateOf(listOf("8:00-9:00"))
    }

    LazyColumn(Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item {
            Cell(
                text = "基本用法", showDivider = true, onClick = {
                    scope.launch {
                        commonState.show()
                    }
                }
            )
        }

        item {
            Cell(
                text = "自定义默认值", showDivider = true, onClick = {
                    scope.launch {
                        customSelectState.show()
                    }
                }
            )
        }
    }
    TimeSelect(
        title = "选择配送时间",
        state = commonState,
        selectKey = selectKey,
        selectTime = selectTime as List<String>,
        onKeyChange = {
            selectKey = it
            selectTime = Collections.EMPTY_LIST
        },
        onTimeChange = { selectTime = it }) {
        timePanel(
            title = "2月23日(今天)",
            key = "2月23日",
            data = listOf("9:00-10:00", "10:00-11:00", "11:00-12:00")
        )
        timePanel(
            title = "2月24日(星期二)",
            key = "2月24日",
            data = listOf("8:00-9:00", "9:00-10:00", "10:00-11:00", "11:00-12:00")
        )
    }
    TimeSelect(
        title = "选择配送时间",
        state = customSelectState,
        selectKey = customSelectKey,
        selectTime = customSelectTime,
        onKeyChange = {
            customSelectKey = it
            customSelectTime = Collections.EMPTY_LIST as List<String>
        },
        onTimeChange = { customSelectTime = it }) {
        timePanel(
            title = "2月23日(今天)",
            key = "2月23日",
            data = listOf("9:00-10:00", "10:00-11:00", "11:00-12:00")
        )
        timePanel(
            title = "2月24日(星期二)",
            key = "2月24日",
            data = listOf("8:00-9:00", "9:00-10:00", "10:00-11:00", "11:00-12:00")
        )
    }
    TimeSelect(
        title = "选择配送时间",
        state = customState,
        selectKey = selectKey,
        selectTime = selectTime as List<String>,
        onKeyChange = {
            selectKey = it
            selectTime = Collections.EMPTY_LIST
        },
        onTimeChange = { selectTime = it },
        timeActiveStyle = MaterialTheme.typography.body2.copy(MaterialTheme.colors.error),
        timeActiveModifier = Modifier
            .background(
                MaterialTheme.colors.error.copy(0.12f),
            )
            .border(
                1.dp,
                MaterialTheme.colors.error,
                RoundedCornerShape(4.dp)
            )
    ) {
        timePanel(
            title = "2月23日(今天)",
            key = "2月23日",
            data = listOf("9:00-10:00", "10:00-11:00", "11:00-12:00")
        )
        timePanel(
            title = "2月24日(星期二)",
            key = "2月24日",
            data = listOf("8:00-9:00", "9:00-10:00", "10:00-11:00", "11:00-12:00")
        )
    }
}