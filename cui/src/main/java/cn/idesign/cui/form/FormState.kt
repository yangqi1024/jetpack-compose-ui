package cn.idesign.cui.form

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun rememberFormItemState(
    initialValue: String = ""
): FormItemState = rememberSaveable(saver = FormItemState.Saver) {
    FormItemState(
        initialValue = initialValue,
    )
}

class FormItemState(
    internal val initialValue: String
) {
    var value by mutableStateOf(initialValue)
    companion object {
        val Saver: Saver<FormItemState, *> = Saver(
            save = {
                it.value
            },
            restore = {
                FormItemState(
                    initialValue = it as String,
                )
            }
        )
    }
}


@Composable
fun rememberFormState(
    initialValue: MutableMap<String, Any> = mutableMapOf()
): FormState = rememberSaveable(saver = FormState.Saver) {
    FormState(
        value = initialValue,
    )
}

class FormState(
    var value: MutableMap<String, Any>
) {

    fun submit() {
        println("---> submit,${value.entries.size}")
        value.entries.forEach {
            println("---> key:${it.key},value:${it.value}")
        }
    }

    companion object {
        val Saver: Saver<FormState, *> = Saver(
            save = {
                it.value
            },
            restore = {
                FormState(
                    value = it,
                )
            }
        )
    }
}