package cn.idesign.cui.form

import androidx.compose.runtime.Composable

public open class FormBuilder {
    private val items = mutableListOf<FormItem>()

    public val formItems
        get() = items

    public fun addItem(item: FormItem) {
        items += item
    }
}

fun FormBuilder.composable(
    label: String?= null,
    name: String?= null,
    type: FormItemType? = FormItemType.Normal,
    state: FormItemState? = null,
    content: @Composable (FormItemState) -> Unit
) {
    addItem(FormItem(label, name,type, state, content))
}

class FormItem(
    val label: String? = null,
    val name: String? = null,
    val  type: FormItemType? = FormItemType.Normal,
    val state: FormItemState?,
    internal val content: @Composable (FormItemState) -> Unit
)

sealed class FormItemType {
    object Normal : FormItemType()
    object Submit : FormItemType()
}