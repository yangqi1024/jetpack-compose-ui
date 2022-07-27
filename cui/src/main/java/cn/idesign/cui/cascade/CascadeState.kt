package cn.idesign.cui.cascade

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import cn.idesign.cui.bottomsheet.BottomSheetState
import cn.idesign.cui.bottomsheet.BottomSheetValue

class CascadeState(initialValue: BottomSheetValue) :BottomSheetState(initialValue) {
}

@Composable
fun rememberCascadeStateState(
    initialValue: BottomSheetValue = BottomSheetValue.Hidden
): BottomSheetState = rememberSaveable(saver = BottomSheetState.SAVER) {
    CascadeState(
        initialValue = initialValue,
    )
}