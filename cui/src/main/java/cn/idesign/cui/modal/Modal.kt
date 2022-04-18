package cn.idesign.cui.modal

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterialApi::class, androidx.compose.ui.ExperimentalComposeUiApi::class)
@Composable
fun Modal(
    state: ModalState = rememberModalState(),
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    onClose: () -> Unit,
    content: @Composable () -> Unit
) {
    if (state.currentValue == ModalValue.Expanded) {
        Dialog(
            onDismissRequest = onClose,
            properties = properties
        ) {
            content()
        }
    }
}

@Composable
fun rememberModalState(
    initialValue: ModalValue = ModalValue.Hidden
): ModalState = rememberSaveable(saver = ModalState.SAVER) {
    ModalState(
        initialValue = initialValue,
    )
}

class ModalState(
    val initialValue: ModalValue,
) {

    private var _initialValue: ModalValue by mutableStateOf(initialValue)

    val currentValue: ModalValue
        get() = _initialValue

    fun show() {
        if (_initialValue != ModalValue.Expanded) {
            _initialValue = ModalValue.Expanded
        }

        Log.d("Modal", "show,_initialValue:${_initialValue}")
    }

    fun hide() {
        if (_initialValue != ModalValue.Hidden) {
            _initialValue = ModalValue.Hidden
        }
    }

    companion object {
        val SAVER: Saver<ModalState, *> = Saver(
            save = {
                it.initialValue
            },
            restore = {
                ModalState(ModalValue.Hidden)
            }
        )
    }
}

enum class ModalValue {

    Hidden,

    Expanded,
}
