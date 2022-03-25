package cn.idesign.cui.actionsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun ActionSheet(
    state: ActionSheetState = rememberActionSheetState(),
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
    state: ActionSheetState = rememberActionSheetState(),
    header: (@Composable () -> Unit)? = null,
    footer: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
    divider: (@Composable () -> Unit)? = {
        Divider()
    },
) {
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = state.covertBottomSheetInitialValue(),
    )
    LaunchedEffect(bottomSheetState) {
        state.bottomSheetState = bottomSheetState
    }
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetBackgroundColor = Color.Transparent,
        sheetContent = {
            Box(
                Modifier
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .background(MaterialTheme.colors.background)
            ) {
                Column {
                    header?.let {
                        it()
                    }
                    content?.let {
                        it()
                    }
                    footer?.let { it() }
                }
            }
        }
    ) {}
}

@Composable
private fun renderItem(action: Action, onClick: (() -> Unit)? = null) {
    ActionSheetItem(
        text = action.text,
        textModifier = action.textModifier,
        textColor = action.textColor,
        secondaryText = action.secondaryText,
        secondaryTextColor = action.secondaryTextColor,
        secondaryTextModifier = action.secondaryTextModifier,
        modifier = action.modifier,
        horizontalAlignment = action.horizontalAlignment,
        verticalArrangement = action.verticalArrangement,
        onClick = onClick
    )
}


@Composable
fun rememberActionSheetState(
    initialValue: ActionSheetValue = ActionSheetValue.Hidden
): ActionSheetState = rememberSaveable(saver = ActionSheetState.Saver) {
    ActionSheetState(
        initialValue = initialValue,
    )
}

class ActionSheetState(
    val initialValue: ActionSheetValue,
) {


    @OptIn(ExperimentalMaterialApi::class)
    internal lateinit var bottomSheetState: ModalBottomSheetState

    @OptIn(ExperimentalMaterialApi::class)
    suspend fun show() {
        bottomSheetState.show()
    }

    @OptIn(ExperimentalMaterialApi::class)
    suspend fun hide() {
        bottomSheetState.hide()
    }

    @OptIn(ExperimentalMaterialApi::class)
    val isVisible: Boolean
        get() = bottomSheetState.isVisible


    @OptIn(ExperimentalMaterialApi::class)
    internal fun covertBottomSheetInitialValue(): ModalBottomSheetValue {
        return when (initialValue) {
            ActionSheetValue.Hidden -> ModalBottomSheetValue.Hidden
            ActionSheetValue.HalfExpanded -> ModalBottomSheetValue.HalfExpanded
            ActionSheetValue.Expanded -> ModalBottomSheetValue.Expanded
        }
    }

    companion object {
        val Saver: Saver<ActionSheetState, *> = Saver(
            save = {
                it.initialValue
            },
            restore = {
                ActionSheetState(ActionSheetValue.Hidden)
            }
        )
    }
}

enum class ActionSheetValue {

    Hidden,

    Expanded,

    HalfExpanded
}
