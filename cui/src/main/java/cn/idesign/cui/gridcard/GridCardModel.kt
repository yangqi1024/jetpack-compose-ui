package cn.idesign.cui.gridcard

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

data class GridCardModel(
    val text: String? = null,
    val textColor: Color = Color.Unspecified,
    val iconPainter: Painter? = null,
    val iconSize: DpSize = DpSize(24.dp, 24.dp),
)
