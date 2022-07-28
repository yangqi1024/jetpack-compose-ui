package cn.idesign.cui.testclient.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
//    primary = Color(0xFF121212),
//    onPrimary = Color.White,
//    error = Red700,
)

private val BlueColorPalette = lightColors(
    primary = Blue500,
    primaryVariant = Blue700,
    secondary = Pink300,
//    error = Red700,
    background = Background,
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    onPrimary = Color.Black,
    secondary = Pink300,
//    error = Red700,
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun CUITestTheme(theme: Theme = Theme.Blue, content: @Composable () -> Unit) {
    val colors = when (theme) {
        Theme.Dark -> DarkColorPalette
        Theme.Light -> LightColorPalette
        Theme.Blue -> BlueColorPalette
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}

enum class Theme {
    Light, Dark,Blue
}