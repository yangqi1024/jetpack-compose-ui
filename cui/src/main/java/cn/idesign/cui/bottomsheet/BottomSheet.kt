package cn.idesign.cui.bottomsheet

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    state: BottomSheetState = rememberBottomSheetState(),
    sheetBackgroundColor: Color = Color.Transparent,
    content: @Composable () -> Unit
) {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = state.covertBottomSheetValue(state.initialValue))
    LaunchedEffect(bottomSheetState) {
        state.bottomSheetState = bottomSheetState
    }
    ModalBottomSheetLayout(
        modifier = modifier,
        sheetState = bottomSheetState,
        sheetBackgroundColor = sheetBackgroundColor,
        sheetContent = {
            content()
        }) { }
}

@Composable
fun rememberBottomSheetState(
    initialValue: BottomSheetValue = BottomSheetValue.Hidden
): BottomSheetState = rememberSaveable(saver = BottomSheetState.SAVER) {
    BottomSheetState(
        initialValue = initialValue,
    )
}

@OptIn(ExperimentalMaterialApi::class)
open class BottomSheetState(
    val initialValue: BottomSheetValue,
) {


    internal lateinit var bottomSheetState: ModalBottomSheetState

    suspend fun show() {
        bottomSheetState.show()

    }

    suspend fun animateTo(targetValue: BottomSheetValue) {
        bottomSheetState.animateTo(covertBottomSheetValue(targetValue))
    }

    suspend fun hide() {
        bottomSheetState.hide()
    }

    val isVisible: Boolean
        get() = bottomSheetState.isVisible


    internal fun covertBottomSheetValue(value: BottomSheetValue): ModalBottomSheetValue {
        return when (value) {
            BottomSheetValue.Hidden -> ModalBottomSheetValue.Hidden
            BottomSheetValue.HalfExpanded -> ModalBottomSheetValue.HalfExpanded
            BottomSheetValue.Expanded -> ModalBottomSheetValue.Expanded
        }
    }

    companion object {
        val SAVER: Saver<BottomSheetState, *> = Saver(
            save = {
                it.initialValue
            },
            restore = {
                BottomSheetState(BottomSheetValue.Hidden)
            }
        )
    }
}

enum class BottomSheetValue {

    Hidden,

    Expanded,

    HalfExpanded
}
