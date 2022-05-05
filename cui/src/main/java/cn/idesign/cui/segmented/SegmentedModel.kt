package cn.idesign.cui.segmented

import androidx.compose.runtime.Composable

data class SegmentedModel(
    val label: String,
    val value: String,
    val disabled: Boolean? = false,
    val icon: (@Composable () -> Unit)? = null,
)
