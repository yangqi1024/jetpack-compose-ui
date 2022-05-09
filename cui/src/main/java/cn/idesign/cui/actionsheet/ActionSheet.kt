package cn.idesign.cui.actionsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cn.idesign.cui.bottomsheet.BottomSheet
import cn.idesign.cui.bottomsheet.BottomSheetState
import cn.idesign.cui.bottomsheet.rememberBottomSheetState
import kotlinx.coroutines.launch


@Composable
fun ActionSheet(
    state: BottomSheetState = rememberBottomSheetState(),
    title: String? = null,
    description: String? = null,
    cancelText: String? = null,
    actions: Array<Action> = arrayOf(),
    onItemClick: ((position: Int) -> Unit)? = null
) {
    val scope = rememberCoroutineScope()
    ActionSheet(
        state = state,
        header = {
            title?.let {
                renderItem(
                    Action(
                        text = title,
                        secondaryText = description
                    )
                )
                Divider()
            }
        },
        footer = {
            cancelText?.let {
                renderItem(
                    Action(
                        text = cancelText,
                    ),
                    onClick = {
                        scope.launch {
                            state.hide()
                        }
                    }
                )
            }
        },
        content = {
            LazyColumn {
                items(actions.size) { index ->
                    val action = actions[index]
                    renderItem(
                        action,
                        onClick = {
                            scope.launch {
                                onItemClick?.let { it(index) }
                            }
                        }
                    )
                    Divider()
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ActionSheet(
    state: BottomSheetState = rememberBottomSheetState(),
    header: (@Composable () -> Unit)? = null,
    footer: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {

    BottomSheet(
        state = state,
        content = {
            Box(
                Modifier
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .background(MaterialTheme.colors.surface)
            ) {
                Column {
                    header?.let {
                        it()
                    }
                    content()
                    footer?.let { it() }
                }
            }
        }
    )
}

@Composable
private fun renderItem(action: Action, onClick: (() -> Unit)? = null) {
    ActionSheetItem(
        modifier = action.modifier,
        text = action.text,
        textModifier = action.textModifier,
        textColor = action.textColor,
        secondaryText = action.secondaryText,
        secondaryTextColor = action.secondaryTextColor,
        secondaryTextModifier = action.secondaryTextModifier,
        horizontalAlignment = action.horizontalAlignment,
        verticalArrangement = action.verticalArrangement,
        onClick = onClick
    )
}


