package cn.idesign.cui.testclient.collapse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cn.idesign.cui.collapse.Collapse

@Composable
fun CollapseTest() {
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
            Collapse {
                collapseItem(title = "一致性 Consistency", key = "0") {
                    Text("与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；")
                    Text("在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。")
                }
                collapseItem(title = "反馈 Feedback", key = "1") {
                    Text("控制反馈：通过界面样式和交互动效让用户可以清晰的感知自己的操作；")
                    Text("页面反馈：操作后，通过页面元素的变化清晰地展现当前状态。")
                }
            }
        }

        item {
            Text(
                text = "指定默认的打开",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .background(color = MaterialTheme.colors.primary.copy(ContentAlpha.medium))
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onPrimary.copy(ContentAlpha.high)
            )
            Collapse(activeKeys = arrayOf("1")) {
                collapseItem(title = "一致性 Consistency", key = "0") {
                    Text("与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；")
                    Text("在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。")
                }
                collapseItem(title = "反馈 Feedback", key = "1") {
                    Text("控制反馈：通过界面样式和交互动效让用户可以清晰的感知自己的操作；")
                    Text("页面反馈：操作后，通过页面元素的变化清晰地展现当前状态。")
                }
            }
        }


        item {
            Text(
                text = "手风琴效果",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .background(color = MaterialTheme.colors.primary.copy(ContentAlpha.medium))
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onPrimary.copy(ContentAlpha.high)
            )
            Collapse(activeKeys = arrayOf("1"), accordion = true) {
                collapseItem(title = "一致性 Consistency", key = "0") {
                    Text("与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；")
                    Text("在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。")
                }
                collapseItem(title = "反馈 Feedback", key = "1") {
                    Text("控制反馈：通过界面样式和交互动效让用户可以清晰的感知自己的操作；")
                    Text("页面反馈：操作后，通过页面元素的变化清晰地展现当前状态。")
                }
                collapseItem(title = "效率 Efficiency", key = "3") {
                    Text("简化流程：设计简洁直观的操作流程；")
                    Text("清晰明确：语言表达清晰且表意明确，让用户快速理解进而作出决策；")
                    Text("帮助用户识别：界面简单直白，让用户快速识别而非回忆，减少用户记忆负担。")
                }
            }
        }
    }
}