package cn.idesign.cui.theme

import androidx.compose.material.MaterialTheme

//private val DarkColorPalette = darkColors(
//   title =
//)
//
//private val LightColorPalette = lightColors(
//    primary = Purple500,
//    primaryVariant = Purple700,
//    secondary = Teal200,
//            background = Background,
//    /* Other default colors to override
//    background = Color.White,
//    surface = Color.White,
//    onPrimary = Color.White,
//    onSecondary = Color.Black,
//    onBackground = Color.Black,
//    onSurface = Color.Black,
//    */
//)
//
//@Composable
//fun CUITheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
//    val colors = if (darkTheme) {
//        DarkColorPalette
//    } else {
//        LightColorPalette
//    }
//
//    MaterialTheme(
//        colors = colors,
//        content = content
//    )
//}

class CUIColor {}


class ComposeUiTheme(
    val colors: CUIColor,
//    val typography: Typography,
//    val shapes: Shapes,


){
   companion object {
        val DEFAULT = ComposeUiTheme(
            CUIColor()
        )
    }
}

val MaterialTheme.CUIColor: CUIColor
    get() = CUIColor()