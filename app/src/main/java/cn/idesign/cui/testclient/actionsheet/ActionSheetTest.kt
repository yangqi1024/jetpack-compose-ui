package cn.idesign.cui.testclient.actionsheet

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cn.idesign.cui.actionsheet.Action
import cn.idesign.cui.actionsheet.ActionSheet
import cn.idesign.cui.actionsheet.ActionSheetState
import cn.idesign.cui.actionsheet.rememberActionSheetState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ActionSheetTest() {
    val context = LocalContext.current

        val simpleState = rememberActionSheetState()
        val leftState = rememberActionSheetState()
        val titleState = rememberActionSheetState()
        val cancelState = rememberActionSheetState()
        val descriptionState = rememberActionSheetState()
        val colorState = rememberActionSheetState()
        val scope = rememberCoroutineScope()
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            ListItem(
                text = { Text("基本用法") },
                modifier = Modifier
                    .height(50.dp)
                    .background(MaterialTheme.colors.background)
                    .clickable {
                        scope.launch {
                            simpleState.show()
                        }
                    }
            )
            Divider()
            ListItem(
                text = { Text("文本居左") },
                modifier = Modifier
                    .height(50.dp)
                    .background(MaterialTheme.colors.background)
                    .clickable {
                        scope.launch {
                            leftState.show()
                        }
                    }

            )
            Divider()
            ListItem(
                text = { Text("包含标题") },
                modifier = Modifier
                    .height(50.dp)
                    .background(MaterialTheme.colors.background)
                    .clickable {
                        scope.launch {
                            titleState.show()
                        }
                    }

            )
            Divider()
            ListItem(
                text = { Text("包含取消按钮") },
                modifier = Modifier
                    .height(50.dp)
                    .background(MaterialTheme.colors.background)
                    .clickable {
                        scope.launch {
                            cancelState.show()
                        }
                    }

            )
            Divider()
            ListItem(
                text = { Text("包含描述") },
                modifier = Modifier
                    .height(50.dp)
                    .background(MaterialTheme.colors.background)
                    .clickable {
                        scope.launch {
                            descriptionState.show()
                        }
                    }

            )
            Divider()
            ListItem(
                text = { Text("自定义颜色") },
                modifier = Modifier
                    .height(50.dp)
                    .background(MaterialTheme.colors.background)
                    .clickable {
                        scope.launch {
                            colorState.show()
                        }
                    }

            )
        }

        Simple(simpleState, context)
        GravityLeft(leftState, context)
        WithTitle(titleState, context)
        CancelTitle(cancelState, context)
        DescriptionSimple(descriptionState, context)
        ColorSimple(colorState, context)
    }

@Composable
private fun Simple(
    actionSheetState: ActionSheetState,
    context: Context
) {
    ActionSheet(
        state = actionSheetState,
        actions = arrayOf(
            Action(
                text = "Item 0",

                ),
            Action(
                text = "Item 1",
            ),
        ),
        onItemClick = {
            Toast.makeText(
                context,
                "Item:${it}",
                Toast.LENGTH_SHORT
            ).show()
        }
    )
}

@Composable
private fun GravityLeft(
    actionSheetState: ActionSheetState,
    context: Context
) {
    ActionSheet(
        state = actionSheetState,
        actions = arrayOf(
            Action(
                text = "Item 0",
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(start = 10.dp)

            ),
            Action(
                text = "Item 1",
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(start = 10.dp)

            ),
        ),
        onItemClick = {
            Toast.makeText(
                context,
                "Item:${it}",
                Toast.LENGTH_SHORT
            ).show()
        }
    )
}

@Composable
private fun WithTitle(
    actionSheetState: ActionSheetState,
    context: Context
) {
    ActionSheet(
        title = "标题",
        state = actionSheetState,
        actions = arrayOf(
            Action(
                text = "Item 0",
            ),
            Action(
                text = "Item 1",
            ),
        ),
        onItemClick = {
            Toast.makeText(
                context,
                "Item:${it}",
                Toast.LENGTH_SHORT
            ).show()
        }
    )
}

@Composable
private fun CancelTitle(
    actionSheetState: ActionSheetState,
    context: Context
) {
    ActionSheet(
        cancelText = "取消",
        state = actionSheetState,
        actions = arrayOf(
            Action(
                text = "Item 0",
            ),
            Action(
                text = "Item 1",
            ),
        ),
        onItemClick = {
            Toast.makeText(
                context,
                "Item:${it}",
                Toast.LENGTH_SHORT
            ).show()
        }
    )
}

@Composable
private fun DescriptionSimple(
    actionSheetState: ActionSheetState,
    context: Context
) {
    ActionSheet(
        cancelText = "取消",
        state = actionSheetState,
        actions = arrayOf(
            Action(
                text = "Item 0",
                secondaryText = "这是Item 0的描述",
            ),
            Action(
                text = "Item 1",
                secondaryText = "这是Item 1的描述",
            ),
        ),
        onItemClick = {
            Toast.makeText(
                context,
                "Item:${it}",
                Toast.LENGTH_SHORT
            ).show()
        }
    )
}

@Composable
private fun ColorSimple(
    actionSheetState: ActionSheetState,
    context: Context
) {
    ActionSheet(
        cancelText = "取消",
        state = actionSheetState,
        actions = arrayOf(
            Action(
                text = "Item 0",
                secondaryText = "这是Item 0的描述",
                textColor = Color.Magenta,
            ),
            Action(
                text = "Item 1",
                secondaryText = "这是Item 1的描述",

                secondaryTextColor = Color.Cyan
            ),
        ),
        onItemClick = {
            Toast.makeText(
                context,
                "Item:${it}",
                Toast.LENGTH_SHORT
            ).show()
        }
    )
}