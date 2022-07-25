package cn.idesign.cui.gridcard

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

open class GridCardModel(
    open val text: String? = null,
    open val textColor: Color = Color.Unspecified,
    open val iconUrl: String? = null,
    open val iconPainter: Painter? = null,
    open val iconSize: DpSize = DpSize(24.dp, 24.dp),
)
